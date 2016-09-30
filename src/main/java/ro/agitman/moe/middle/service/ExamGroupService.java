package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.ExamGroup;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.web.dto.ExamGroupDTO;

import java.util.List;

/**
 * Created by d-uu31cq on 19.09.2016.
 */
public interface ExamGroupService {

    List<ExamGroup> findAll(User user);

    ExamGroup createGroup(User user, ExamGroupDTO groupDTO);

    void remove(Integer groupId);

    void addStudent(User student, User user);
}
