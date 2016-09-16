package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ExamItemAnswer;
import ro.agitman.moe.web.dto.ItemAnswerDTO;

import java.util.List;

/**
 * Created by edi on 9/14/16.
 */
public interface ItemAnswerService {

    List<ExamItemAnswer> getForItem(ExamItem examItem);

    ExamItemAnswer getById(Integer id);

    void persist(ItemAnswerDTO itemAnswer, Integer itemId);

    void delete(Integer id);
}
