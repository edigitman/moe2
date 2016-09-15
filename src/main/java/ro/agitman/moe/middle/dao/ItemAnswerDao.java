package ro.agitman.moe.middle.dao;

import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ExamItemAnswer;

import java.util.List;

/**
 * Created by edi on 9/14/16.
 */
public interface ItemAnswerDao {

    ExamItemAnswer getByKey(Integer key);

    void persist(ExamItemAnswer entity);

    void update(ExamItemAnswer entity);

    void delete(ExamItemAnswer entity);

    List<ExamItemAnswer> findByItem(ExamItem item);

}
