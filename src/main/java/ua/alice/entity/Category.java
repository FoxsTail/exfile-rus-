package ua.alice.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "id_category")
    private Integer idc;

    @Column(name = "name_category", length = 60)
    private String name;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private ExFile exFile;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "getter_category", cascade = CascadeType.ALL)
    private List<ExFile> exFiles = new ArrayList<>();

    //-----конструктори
    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }

    //------методи
    public void addFile(ExFile exFile) {
        exFiles.add(exFile);
    }

    //--------------гетери і сетери

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public ExFile getExFile() {
        return exFile;
    }

    public void setExFile(ExFile exFile) {
        this.exFile = exFile;
    }

    public List<ExFile> getExFiles() {
        return exFiles;
    }

    public void setExFiles(List<ExFile> exFiles) {
        this.exFiles = exFiles;
    }
}


































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































