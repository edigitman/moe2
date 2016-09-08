package ro.agitman.moe.middle.dao;

import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.User;

import java.util.List;

/**
 * Created by edi on 8/20/16.
 */
public interface ExamDao {

    List<Exam> findRecentCount(int count, User user);

    List<Exam> findAll(User user);

    void persist(Exam entity);

    void update(Exam entity);

    Exam getByKey(Integer key);

}
