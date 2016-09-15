package ro.agitman.moe.web.dto;

import ro.agitman.moe.middle.model.ExamItemAnswer;

/**
 * Created by edi on 9/14/16.
 */
public class ItemAnswerDTO {

    private Integer id;
    private Boolean correct;
    private String value;

    public ItemAnswerDTO() {
    }

    public ItemAnswerDTO(ExamItemAnswer examItemAnswer) {
        this.id = examItemAnswer.getId();
        this.correct = examItemAnswer.getCorrect();
        this.value = examItemAnswer.getValue();
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
}
