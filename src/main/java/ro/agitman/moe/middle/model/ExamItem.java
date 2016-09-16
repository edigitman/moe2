package ro.agitman.moe.middle.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXAM_ITEM")
@SequenceGenerator(name = "EXAM_ITEM_SEQ", initialValue = 1000)
public class ExamItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_ITEM_SEQ")
    private Integer id;

    @NotNull
    private String assertion;
    @NotNull
    private String title;

    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
    private Integer points;

    @Enumerated(EnumType.STRING)
    private ItemTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXAM_ID", nullable = false)
    private Exam exam;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private List<ExamItemAnswer> answerList;


    private Integer ord;
    private Date datecreated;

    public ExamItem(Integer examId) {
        this.id = examId;
    }

    public ExamItem() {
    }

    public ExamItem(ExamItem item) {
        this.assertion = item.getAssertion();
        this.title = item.getTitle();
        this.difficulty = item.getDifficulty();
        this.points = item.getPoints();
        this.type = item.getType();
        this.ord = item.getOrd();
    }

    @Override
    public String toString() {
        return "ExamItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", assertion='" + assertion + '\'' +
                ", type=" + type +
                ", exam=" + exam.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamItem examItem = (ExamItem) o;

        if (!id.equals(examItem.id)) return false;
        if (!assertion.equals(examItem.assertion)) return false;
        if (difficulty != null ? !difficulty.equals(examItem.difficulty) : examItem.difficulty != null) return false;
        if (points != null ? !points.equals(examItem.points) : examItem.points != null) return false;
        if (!type.equals(examItem.type)) return false;
        return !(datecreated != null ? !datecreated.equals(examItem.datecreated) : examItem.datecreated != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + assertion.hashCode();
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum type) {
        this.type = type;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public List<ExamItemAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ExamItemAnswer> answerList) {
        this.answerList = answerList;
    }
}
