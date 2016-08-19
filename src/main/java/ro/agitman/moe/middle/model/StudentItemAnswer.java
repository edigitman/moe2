package ro.agitman.moe.middle.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "STUDENT_ITEM_ANSWER")
@SequenceGenerator(name = "STUDENT_ITEM_ANSWER_SEQ", initialValue = 1000)
public class StudentItemAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ITEM_ANSWER_SEQ")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTANCE_ID", nullable = false)
    private StudentExamInstance studentExamInstance;

    @OneToOne
    private ExamItem examItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User owner;

    private String value;
    private String rawAnswer;

    private Boolean solvable;
    private Boolean reviewed;
    private Boolean correct;
    private Integer points;
    private Date datecreated;

    public StudentItemAnswer(Integer id) {
        this.id = id;
    }

    public StudentItemAnswer() {
    }

    @Override
    public String toString() {
        return "StudentExamAnswer{" +
                "id=" + id +
                ", studentExamInstance=" + studentExamInstance +
                ", examItem=" + examItem +
                ", owner=" + owner.toString() +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentItemAnswer that = (StudentItemAnswer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentExamInstance != null ? !studentExamInstance.equals(that.studentExamInstance) : that.studentExamInstance != null)
            return false;
        if (examItem != null ? !examItem.equals(that.examItem) : that.examItem != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentExamInstance != null ? studentExamInstance.hashCode() : 0);
        result = 31 * result + (examItem != null ? examItem.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentExamInstance getStudentExamInstance() {
        return studentExamInstance;
    }

    public void setStudentExamInstance(StudentExamInstance studentExamInstance) {
        this.studentExamInstance = studentExamInstance;
    }

    public ExamItem getExamItem() {
        return examItem;
    }

    public void setExamItem(ExamItem examItem) {
        this.examItem = examItem;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getRawAnswer() {
        return rawAnswer;
    }

    public void setRawAnswer(String rawAnswer) {
        this.rawAnswer = rawAnswer;
    }

    public Boolean getSolvable() {
        return solvable;
    }

    public void setSolvable(Boolean solvable) {
        this.solvable = solvable;
    }


    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewd) {
        this.reviewed = reviewd;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
