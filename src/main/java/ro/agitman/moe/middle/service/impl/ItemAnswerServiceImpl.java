package ro.agitman.moe.middle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.ItemAnswerDao;
import ro.agitman.moe.middle.dao.ItemDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ExamItemAnswer;
import ro.agitman.moe.middle.service.ItemAnswerService;
import ro.agitman.moe.web.dto.ItemAnswerDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by edi on 9/14/16.
 */
@Service("itemAnswerService")
@Transactional
public class ItemAnswerServiceImpl implements ItemAnswerService {

    @Autowired
    private ItemAnswerDao answerDao;
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ExamItemAnswer> getForItem(ExamItem examItem) {
        return answerDao.findByItem(examItem);
    }

    @Override
    public ExamItemAnswer getById(Integer id) {
        return answerDao.getByKey(id);
    }

    @Override
    public void persist(ItemAnswerDTO itemAnswer, Integer itemId) {

        ExamItem item  = itemDao.getByKey(itemId);

        ExamItemAnswer answer = new ExamItemAnswer();
        answer.setItem(item);
        answer.setValue(itemAnswer.getValue());
        answer.setCorrect(itemAnswer.getCorrect());
        answer.setDatecreated(new Date());

        answerDao.persist(answer);

        itemAnswer.setId(answer.getId());
    }

    @Override
    public void delete(Integer id) {
        answerDao.delete(answerDao.getByKey(id));
    }

}
