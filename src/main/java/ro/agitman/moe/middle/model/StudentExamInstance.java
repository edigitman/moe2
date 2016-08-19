package ro.agitman.moe.middle.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "STUDENT_EXAM_INSTANCE")
@SequenceGenerator(name = "STUDENT_EXAM_INSTANCE_SEQ", initialValue = 1000)
public class StudentExamInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_EXAM_INSTANCE_SEQ")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXAM_ID", nullable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User stud;

    private InstanceStatusEnum status;

    private Integer points;

    private Date datecreated;
    private Date dateupdated;

    public StudentExamInstance(Integer id) {
        this.id = id;
    }

    public StudentExamInstance() {
    }

    @Override
    public String toString() {
        return "StudentExamInstance{" +
                "id=" + id +
                ", exam=" + exam +
                ", stud=" + stud +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentExamInstance that = (StudentExamInstance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (exam != null ? !exam.equals(that.exam) : that.exam != null) return false;
        if (stud != null ? !stud.equals(that.stud) : that.stud != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (datecreated != null ? !datecreated.equals(that.datecreated) : that.datecreated != null) return false;
        return dateupdated != null ? dateupdated.equals(that.dateupdated) : that.dateupdated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (exam != null ? exam.hashCode() : 0);
        result = 31 * result + (stud != null ? stud.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        result = 31 * result + (dateupdated != null ? dateupdated.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getStud() {
        return stud;
    }

    public void setStud(User stud) {
        this.stud = stud;
    }

    public InstanceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InstanceStatusEnum status) {
        this.status = status;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Date dateupdated) {
        this.dateupdated = dateupdated;
    }

    public void setDateupdated(Timestamp dateupdated) {
        this.dateupdated = dateupdated;
    }
}
