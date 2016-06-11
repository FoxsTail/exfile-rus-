package ua.alice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "id_department")
    private Integer idd;

    @Column(name = "name_department", length = 50)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "getter_departments", cascade = CascadeType.ALL)
    private List<ExFile> exFiles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "departments", cascade = CascadeType.ALL)
    private List<Subdivision> subdivisions = new ArrayList<>();


    //-----конструктори
    public Department() {
    }

    public Department(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Department(String name) {
        this.name = name;
    }

    //------методи
    public void addUser(User user) {
        user.setDepartment(this);
        users.add(user);
    }

    public void addFile(ExFile exFile) {
        exFiles.add(exFile);
    }

    public void addSubdivision(Subdivision subdivision) {
        subdivisions.add(subdivision);
    }

    //--------------гетери і сетери

    public Integer getIdd() {
        return idd;
    }

    public void setIdd(Integer idd) {
        this.idd = idd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ExFile> getExFiles() {
        return exFiles;
    }

    public void setExFiles(List<ExFile> exFiles) {
        this.exFiles = exFiles;
    }

    public List<Subdivision> getSubdivisions() {
        return subdivisions;
    }

    public void setSubdivisions(List<Subdivision> subdivisions) {
        this.subdivisions = subdivisions;
    }
}
