package ro.agitman.moe.middle.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ItemDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;

import java.util.List;

/**
 * Created by d-uu31cq on 08.09.2016.
 */
@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, ExamItem> implements ItemDao {

    @Override
    public List<ExamItem> findByExam(Exam exam) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("exam", exam));
        return crit.list();
    }


}
