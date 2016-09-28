package ro.agitman.moe.middle.dao;

import ro.agitman.moe.middle.model.ExamGroup;
import ro.agitman.moe.middle.model.User;

import java.util.List;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
public interface ExamGroupDao {

    ExamGroup getByKey(Integer key);

    List<ExamGroup> findForOwner(User user);

    void persist(ExamGroup entity);

    void update(ExamGroup entity);

    void delete(ExamGroup entity);
}
