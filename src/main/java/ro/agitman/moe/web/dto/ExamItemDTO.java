package ro.agitman.moe.web.dto;

import ro.agitman.moe.middle.model.ItemTypeEnum;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
public class ExamItemDTO {

    private String assertion;
    private Integer points;
    private ItemTypeEnum type;
    private Integer examId;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
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

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum itemTypeEnum) {
        this.type = itemTypeEnum;
    }
}
