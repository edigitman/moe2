package ro.agitman.moe.middle.dao;

import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;

import java.util.List;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
public interface ItemDao {

    ExamItem getByKey(Integer key);

    void persist(ExamItem entity);

    void update(ExamItem entity);

    void delete(ExamItem entity);

    List<ExamItem> findByExam(Exam exam);
}
