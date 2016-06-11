package ua.alice.entity;

/**
 * Created by Лис on 26.05.2016.
 */

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;

    @Size(min = 2, max = 20, message="Слишком короткий логин")
    @Pattern(regexp = "[A-Z][a-z]+", message="Логин должен соответствовать формату [A-Z][a-z]+")
    @Column(name = "login_user", unique = true)
    private String login;

   @Size(min = 6, message="Слишком короткий пароль")
    @Column(name = "password_user")
    private String password;

    @Size(min = 1, max = 20, message="Слишком короткое имя")
    @Pattern(regexp = "[А-Я][а-я]+", message="Имя должен соответствовать формату [А-Я][а-я]+")
    @Column(name = "name_user")
    private String name;

    @Size(min = 5, max = 20, message="Фамилия из 1й буквы? Не верю=)")
    @Pattern(regexp = "[А-Я][а-я]+", message="Фамилия должна соответствовать формату [А-Я][а-я]+")
    @Column(name = "surname_user")
    private String surname;

    @Size(min = 2, max = 20, message="Отчество из 1й буквы? Правда?.")
    @Pattern(regexp = "[А-Я][а-я]+", message="Отчество должно соответствовать формату [А-Я][а-я]+")
    @Column(name = "patronymic_user")
    private String patronymic;

    @NotNull(message="Email должен быть задан")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "заданный email не может существовать")
    @Email(message="Invalid email")
    @Column(name = "email_user", unique = true)
    private String email;

    @Column(name = "inn_user", unique = true)
    private String inn;
//----зовнішні зв'язки-----//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subdivision")
    private Subdivision subdivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExFile> exFiles = new ArrayList<>();
//-----------//
    @Column(name = "role_user")
    private Role role;

    @Pattern(regexp = "^[1-9][0-9]*$", message="Выберите отдел")
    @Transient
    private String department_trans;

    @Pattern(regexp = "^[1-9][0-9]*$", message="Выберите подразделение")
    @Transient
    private String subdivision_trans;


//-----конструктори

    public User() {
    }

    public User(String login, String password, String name, Role role, String surname, String patronymic, String email, String inn) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.inn = inn;
    }

    //------методи

    public ExFile getLastAddedFile(){
        return exFiles.get(exFiles.size()-1);
    }
    public void addExFile(ExFile exFile){
        exFile.setUser(this);
        exFiles.add(exFile);
    }


    //--------------гетери і сетери

    public List<ExFile> getExFiles() {
        return exFiles;
    }

    public void setExFiles(List<ExFile> exFiles) {
        this.exFiles = exFiles;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getDepartment_trans() {
        return department_trans;
    }

    public void setDepartment_trans(String department_trans) {
        this.department_trans = department_trans;
    }

    public String getSubdivision_trans() {
        return subdivision_trans;
    }

    public void setSubdivision_trans(String subdivision_trans) {
        this.subdivision_trans = subdivision_trans;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getInn() {
        return inn;
    }

    public Long getId() {
        return id;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public Department getDepartment() {
        return department;
    }

    public String getSurname() {
        return surname;
    }

}