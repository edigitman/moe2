package ro.agitman.moe.middle.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ExamDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.User;

import java.util.List;

@Repository("examDao")
public class ExamDaoImpl extends AbstractDao<Integer, Exam> implements ExamDao {

    static final Logger logger = LoggerFactory.getLogger(ExamDaoImpl.class);

    @Override
    public List<Exam> findRecentCount(int count, User user) {
        Criteria criteria = createEntityCriteria()
                .add(Restrictions.eq("owner", user))
                .addOrder(Order.desc("id"))
                .setFetchSize(count);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Exam> exams = (List<Exam>) criteria.list();

        return exams;
    }

    @Override
    public List<Exam> findAll(User user) {
        Criteria criteria = createEntityCriteria()
                .add(Restrictions.eq("owner", user))
                .addOrder(Order.desc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Exam> exams = (List<Exam>) criteria.list();

        return exams;
    }
}
