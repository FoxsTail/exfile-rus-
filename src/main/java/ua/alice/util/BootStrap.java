package ua.alice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alice.entity.*;
import ua.alice.repository.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * Created by Лис on 26.05.2016.
 */
@Component
public class BootStrap {
    @Autowired
    private ExFileJpaRepository exFileJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private SubdivisionJpaRepository subdivisionJpaRepository;

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;
    @Autowired
    private CategoryJpaRepository categoryJpaRepository;


    @PostConstruct
    public void init() {

        System.err.println("-------iniiiiiiiiiiiiiiiiiiiit-------");
        System.setProperty("console.encoding","Cp866");

        Department department1 = new Department("Хирургия");
        Department department2 = new Department("Гинекология");
        Department department3 = new Department("Урология");
        Department department4 = new Department("Реанимация");
        Department department5 = new Department("Психиатрия");

        Subdivision subdivision1 = new Subdivision("Левобережное");
        Subdivision subdivision2 = new Subdivision("Центральное");
        Subdivision subdivision3 = new Subdivision("Правобережное");

        Category category1 = new Category("Срочно");
        Category category2 = new Category("Операция");
        Category category3 = new Category("Анализы");
        Category category4 = new Category("Снимки");

        categoryJpaRepository.save(category1);
        categoryJpaRepository.save(category2);
        categoryJpaRepository.save(category3);
        categoryJpaRepository.save(category4);

        departmentJpaRepository.save(department1);
        departmentJpaRepository.save(department2);
        departmentJpaRepository.save(department3);
        departmentJpaRepository.save(department4);
        departmentJpaRepository.save(department5);

        subdivisionJpaRepository.save(subdivision1);
        subdivisionJpaRepository.save(subdivision2);
        subdivisionJpaRepository.save(subdivision3);

        subdivision1.addDepartment(department1, department2, department3, department5);
        subdivision2.addDepartment(department1, department2, department3, department4, department5);
        subdivision3.addDepartment(department1, department2, department4, department5);


        User user = new User("Admin", "1111111", "Админ", Role.ADMIN, "Вселенский", "Властелинович", "somemail@gmail.com", "45678912378");
        User user1 = new User("Dominic", "22222222", "Иаков", Role.USER, "Берман", "Соломонович", "magic@ymail.com", "78945612345");
        User user2 = new User("Amina", "33333333", "Анна", Role.USER, "Мирончук", "Валериевна", "A_valera@ymail.com", "45645612312");
        User user3 = new User("Lord", "33333333", "Петр", Role.USER, "Хомяков", "Владиславович", "saint_peter@ymail.com", "98745638543");


        department1.addUser(user);
        subdivision1.addUser(user);

        department2.addUser(user1);
        subdivision2.addUser(user1);

        department3.addUser(user2);
        subdivision3.addUser(user2);

        department5.addUser(user3);
        subdivision2.addUser(user3);

        user.setDepartment(department1);
        user.setSubdivision(subdivision1);

        userJpaRepository.save(user);
        userJpaRepository.save(user1);
        userJpaRepository.save(user2);
        userJpaRepository.save(user3);

    }
}
