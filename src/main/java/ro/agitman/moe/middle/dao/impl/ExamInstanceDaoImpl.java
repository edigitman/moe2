package ro.agitman.moe.middle.dao.impl;

import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ExamInstanceDao;
import ro.agitman.moe.middle.model.ExamInstance;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
@Repository("examInstanceDao")
public class ExamInstanceDaoImpl extends AbstractDao<Integer, ExamInstance> implements ExamInstanceDao {

}
