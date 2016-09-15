package ro.agitman.moe.middle.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ItemAnswerDao;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ExamItemAnswer;

import java.util.List;

/**
 * Created by edi on 9/14/16.
 */
@Repository("itemAnswerDao")
public class ItemAnswerDaoImpl extends AbstractDao<Integer, ExamItemAnswer> implements ItemAnswerDao {


    @Override
    public List<ExamItemAnswer> findByItem(ExamItem item) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("item", item));
        return crit.list();
    }
}
