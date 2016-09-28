package ro.agitman.moe.web.dto;

import java.util.List;

/**
 * Created by d-uu31cq on 19.09.2016.
 */
public class ExamGroupDTO {

    private String groupName;
    private List<String> students;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
