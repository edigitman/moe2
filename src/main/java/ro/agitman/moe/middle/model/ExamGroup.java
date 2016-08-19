package ro.agitman.moe.middle.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXAM_GROUP")
@SequenceGenerator(name = "EXAM_GROUP_SEQ", initialValue = 1000)
public class ExamGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_GROUP_SEQ")
    private Integer id;
    private String name;


    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EXAM_GROUP_APP_USER",
            joinColumns = {@JoinColumn(name = "EXAM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> students;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User owner;

    private Boolean locked;
    private Date datecreated;

    public ExamGroup(Integer id) {
        this.id = id;
    }

    public ExamGroup() {
    }

    @Override
    public String toString() {
        return "ExamGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamGroup examGroup = (ExamGroup) o;

        if (id != null ? !id.equals(examGroup.id) : examGroup.id != null) return false;
        if (name != null ? !name.equals(examGroup.name) : examGroup.name != null) return false;
        if (owner != null ? !owner.equals(examGroup.owner) : examGroup.owner != null) return false;
        return datecreated != null ? datecreated.equals(examGroup.datecreated) : examGroup.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
