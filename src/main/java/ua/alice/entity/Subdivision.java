package ua.alice.entity;

import javax.persistence.*;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subdivisions")
public class Subdivision {
    @Id
    @GeneratedValue
    @Column(name = "id_subdivision")
    private Integer ids;

    @Column(name = "name_subdivision", length = 60)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subdivision", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "getter_subdivisions", cascade = CascadeType.ALL)
    private List<ExFile> exFiles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "subdivision_category", joinColumns = @JoinColumn(name = "ids", referencedColumnName = "id_subdivision"),
            inverseJoinColumns = @JoinColumn(name = "idd", referencedColumnName = "id_department"))
    private List<Department> departments = new ArrayList<>();

    //-----конструктори

    Subdivision() {
    }

    public Subdivision(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Subdivision(String name) {
        this.name = name;
    }

    //------методи
    public void addUser(User user) {
        user.setSubdivision(this);
        users.add(user);
    }

    public void addFile(ExFile exFile) {
        exFiles.add(exFile);
    }

    public void addDepartment(Department... department) {
        for (Department dep : department) {
            dep.addSubdivision(this);
            departments.add(dep);
        }
    }

    //--------------гетери і сетери


    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ExFile> getExFiles() {
        return exFiles;
    }

    public void setExFiles(List<ExFile> exFiles) {
        this.exFiles = exFiles;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
