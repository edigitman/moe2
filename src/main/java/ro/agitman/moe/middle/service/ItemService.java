package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.web.dto.ExamItemDTO;

import java.util.List;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
public interface ItemService {

    void createItem(ExamItemDTO itemDTO);

    List<ExamItem> findByExam(Exam exam);

    ExamItem getByKey(Integer id);



}
