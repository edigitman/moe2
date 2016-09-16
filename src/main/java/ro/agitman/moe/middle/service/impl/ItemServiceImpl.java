package ro.agitman.moe.middle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.ExamDao;
import ro.agitman.moe.middle.dao.ItemDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.service.ItemService;
import ro.agitman.moe.web.dto.ExamItemDTO;

import java.util.List;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ExamDao examDao;
    @Autowired
    private ItemDao itemDao;

    @Override
    public void createItem(ExamItemDTO itemDTO) {

        Exam exam = examDao.getByKey(itemDTO.getExamId());

        ExamItem item;
        if (itemDTO.getId() != null) {
            // update
            item = itemDao.getByKey(itemDTO.getId());
        } else {
            // create
            item = new ExamItem();
            item.setExam(exam);
        }

        item.setType(itemDTO.getType());
        item.setAssertion(itemDTO.getAssertion());
        item.setPoints(itemDTO.getPoints());
        item.setTitle(buildTitle(item.getAssertion()));

        exam.setPoints(exam.getPoints() + item.getPoints());
        exam.setItems(exam.getItems() + 1);
        examDao.update(exam);
        if (itemDTO.getId() != null) {
            itemDao.update(item);
        } else {
            itemDao.persist(item);
        }

        itemDTO.setId(item.getId());

    }

    private String buildTitle(String assertion) {
        return assertion;
    }

    @Override
    public List<ExamItem> findByExam(Exam exam) {
        return itemDao.findByExam(exam);
    }

    @Override
    public ExamItem getByKey(Integer id) {
        return itemDao.getByKey(id);
    }

    public void remove(Integer itemId) {

        ExamItem item = itemDao.getByKey(itemId);
        Exam exam = item.getExam();
        exam.setPoints(exam.getPoints() - item.getPoints());
        exam.setItems(exam.getItems() - 1);
        examDao.update(exam);

        itemDao.delete(item);
    }
}
