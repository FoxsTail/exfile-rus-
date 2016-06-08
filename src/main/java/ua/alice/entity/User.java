package ua.alice.entity;

/**
 * Created by Лис on 26.05.2016.
 */

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "login_user", unique = true)
    private String login;

   @Size(min = 6, max = 20)
    @Column(name = "password_user")
    private String password;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[А-Я][а-я]+")
    @Column(name = "name_user")
    private String name;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[А-Я][а-я]+")
    @Column(name = "surname_user")
    private String surname;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[А-Я][а-я]+")
    @Column(name = "patronymic_user")
    private String patronymic;

    @Email
    @Column(name = "email_user", unique = true)
    private String email;

    @Column(name = "inn_user")
    private String inn;
//---------//
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

    @Pattern(regexp = "^[1-9][0-9]*$")
    @Transient
    private String department_trans;

    @Pattern(regexp = "^[1-9][0-9]*$")
    @Transient
    private String subdivision_trans;


//-----------constructors

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

//-------------methods

    public ExFile getLastAddedFile(){
        return exFiles.get(exFiles.size()-1);
    }
    public void addExFile(ExFile exFile){
        exFile.setUser(this);
        exFiles.add(exFile);
    }


//---------------getters and setters


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