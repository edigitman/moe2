package ro.agitman.moe.web.dto;

import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ItemTypeEnum;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
public class ExamItemDTO {

    private String assertion;
    private String title;
    private Integer points;
    private ItemTypeEnum type;
    private Integer examId;
    private Integer id;
    private Boolean selected;

    public ExamItemDTO() {
    }

    public ExamItemDTO(ExamItem examItem) {

        this.assertion = examItem.getAssertion();
        this.points = examItem.getPoints();
        this.type = examItem.getType();
        this.id = examItem.getId();
        this.title = examItem.getTitle();
    }

    public ExamItemDTO(ExamItem examItem, Boolean selected) {

        this.assertion = examItem.getAssertion();
        this.points = examItem.getPoints();
        this.type = examItem.getType();
        this.id = examItem.getId();
        this.title = examItem.getTitle();
        this.selected = selected;
    }


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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
