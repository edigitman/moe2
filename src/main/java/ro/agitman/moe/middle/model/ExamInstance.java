package ro.agitman.moe.middle.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "EXAM_INSTANCE")
@SequenceGenerator(name = "EXAM_INSTANCE_SEQ", initialValue = 1000)
public class ExamInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_INSTANCE_SEQ")
    private Integer id;

    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private InstanceStatusEnum status;

    private Date startdate;
    private Date enddate;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXAM_ID", nullable = false)
    private Exam exam;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private ExamGroup group;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User owner;

    private Boolean autoSolved;
    private Date datecreated;

    public ExamInstance(Integer id) {
        this.id = id;
    }

    public ExamInstance() {
    }

    @Override
    public String toString() {
        return "ExamInstance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", startdate=" + startdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamInstance that = (ExamInstance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
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

    public InstanceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InstanceStatusEnum status) {
        this.status = status;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamGroup getGroup() {
        return group;
    }

    public void setGroup(ExamGroup group) {
        this.group = group;
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

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Boolean getAutoSolved() {
        return autoSolved;
    }

    public void setAutoSolved(Boolean autoSolved) {
        this.autoSolved = autoSolved;
    }
}
