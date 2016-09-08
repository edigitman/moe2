package ro.agitman.moe.web.dto;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
public class ExamEditDTO {

    private String name;
    private String value;
    private Integer pk;

    public ExamEditDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }
}
