package ua.alice.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: заменить ручные закидки на подгрузку информации файла
@Entity
@Table(name = "files")
public class ExFile {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_path")
    private String path;

    @Column(name = "file_name")
    private String name;

    @Column(name = "size_file")
    private Long size;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "[А-Я][а-я]+")
    @Column(name = "about_file")
    private String about;

    @Column(name = "file_add_datatime")
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_subdivision")
    private Subdivision sender_subdivision;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department sender_department;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "exFiles_getter_subdivisions", joinColumns = @JoinColumn(name = "id", referencedColumnName = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "ids", referencedColumnName = "id_subdivision"))
    private List<Subdivision> getter_subdivisions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "exFiles_getter_departments", joinColumns = @JoinColumn(name = "id", referencedColumnName = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "idd", referencedColumnName = "id_department"))
    private List<Department> getter_departments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "exFiles_getter_category", joinColumns = @JoinColumn(name = "id", referencedColumnName = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "idc", referencedColumnName = "id_category"))
    private List<Category> getter_category = new ArrayList<>();

    @Transient
    private MultipartFile multipartFilefile;

    @Transient
    private String[] value_categories;

    @Transient
    private String[] value_subdivisions;

    @Transient
    private String[] value_departments;


    public ExFile() {
    }

    public ExFile(String name, Long size, String about) {
        this.name = name;
        this.size = size;
        this.about = about;
    }

    public void addGetterCategory(Category category) {
        category.addFile(this);
        getter_category.add(category);
    }


    public void addGetterSubdivision(Subdivision subdivision) {
        subdivision.addFile(this);
        getter_subdivisions.add(subdivision);
    }

    public void addGetterDepartment(Department department) {
        department.addFile(this);
        getter_departments.add(department);
    }

//------------------------getters & setters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getMultipartFilefile() {
        return multipartFilefile;
    }

    public void setMultipartFilefile(MultipartFile multipartFilefile) {
        this.multipartFilefile = multipartFilefile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Subdivision getSender_subdivision() {
        return sender_subdivision;
    }

    public void setSender_subdivision(Subdivision sender_subdivision) {
        this.sender_subdivision = sender_subdivision;
    }

    public Department getSender_department() {
        return sender_department;
    }

    public void setSender_department(Department sender_department) {
        this.sender_department = sender_department;
    }


    public List<Subdivision> getGetter_subdivisions() {
        return getter_subdivisions;
    }

    public void setGetter_subdivisions(List<Subdivision> getter_subdivisions) {
        this.getter_subdivisions = getter_subdivisions;
    }

    public List<Department> getGetter_departments() {
        return getter_departments;
    }

    public void setGetter_departments(List<Department> getter_departments) {
        this.getter_departments = getter_departments;
    }

    public String[] getValue_subdivisions() {
        return value_subdivisions;
    }

    public void setValue_subdivisions(String[] value_subdivisions) {
        this.value_subdivisions = value_subdivisions;
    }

    public String[] getValue_departments() {
        return value_departments;
    }

    public void setValue_departments(String[] value_departments) {
        this.value_departments = value_departments;
    }

    public List<Category> getGetter_category() {
        return getter_category;
    }

    public void setGetter_category(List<Category> getter_category) {
        this.getter_category = getter_category;
    }

    public String[] getValue_categories() {
        return value_categories;
    }

    public void setValue_categories(String[] value_categories) {
        this.value_categories = value_categories;
    }


}
