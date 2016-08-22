package ro.agitman.moe.middle.dao;

import ro.agitman.moe.middle.model.Exam;

import java.util.List;

/**
 * Created by edi on 8/20/16.
 */
public interface ExamDao {

    List<Exam> findRecentCount(int count);

}
