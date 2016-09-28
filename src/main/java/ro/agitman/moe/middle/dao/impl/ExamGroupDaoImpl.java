package ro.agitman.moe.middle.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ExamGroupDao;
import ro.agitman.moe.middle.model.ExamGroup;
import ro.agitman.moe.middle.model.User;

import java.util.List;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
@Repository("examGroupDao")
public class ExamGroupDaoImpl extends AbstractDao<Integer, ExamGroup> implements ExamGroupDao {

    @Override
    public List<ExamGroup> findForOwner(User user){

        Criteria criteria = createEntityCriteria()
                .add(Restrictions.eq("owner", user))
                .addOrder(Order.desc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ExamGroup> groups = (List<ExamGroup>) criteria.list();

        return groups;
    }
}
