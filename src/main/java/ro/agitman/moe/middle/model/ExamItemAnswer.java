package ro.agitman.moe.middle.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "ITEM_ANSWER")
@SequenceGenerator(name = "ITEM_ANSWER_SEQ", initialValue = 1000)
public class ExamItemAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ANSWER_SEQ")
    private Integer id;
    private Boolean correct;
    private String value;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ExamItem item;

    private Date datecreated;

    public ExamItemAnswer(Integer id) {
        this.id = id;
    }

    public ExamItemAnswer() {
    }

    public ExamItemAnswer(ExamItemAnswer answer) {
        this.correct = answer.getCorrect();
        this.value = answer.getValue();
    }

    @Override
    public String toString() {
        return "ExamItemAnswer{" +
                "id=" + id +
                ", correct=" + correct +
                ", value='" + value + '\'' +
                ", item=" + item.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamItemAnswer that = (ExamItemAnswer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return datecreated != null ? datecreated.equals(that.datecreated) : that.datecreated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
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

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ExamItem getItem() {
        return item;
    }

    public void setItem(ExamItem item) {
        this.item = item;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}
