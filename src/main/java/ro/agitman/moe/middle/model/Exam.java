package ro.agitman.moe.middle.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "EXAM")
@SequenceGenerator(name = "EXAM_SEQ", initialValue = 1000)
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_SEQ")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User owner;

    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
    private Integer points;
    private Integer items;
    //when a new exam instance is created the exam is blocked for editing
    private Boolean locked;
    private Date datecreated;

    public Exam() {
    }

    public Exam(Exam exam) {
        this.name = exam.getName();
        this.owner = exam.getOwner();
        this.difficulty = exam.getDifficulty();
        this.points = exam.getPoints();
        this.items = exam.getItems();
        this.locked = false;
    }

    public Exam(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (!id.equals(exam.id)) return false;
        if (!name.equals(exam.name)) return false;
        if (!owner.equals(exam.owner)) return false;
        if (difficulty != null ? !difficulty.equals(exam.difficulty) : exam.difficulty != null) return false;
        return datecreated != null ? datecreated.equals(exam.datecreated) : exam.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
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

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
}
