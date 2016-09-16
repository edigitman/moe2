package ro.agitman.moe.middle.dao.impl;

import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.ExamGroupDao;
import ro.agitman.moe.middle.model.ExamGroup;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
@Repository("examGroupDao")
public class ExamGroupDaoImpl extends AbstractDao<Integer, ExamGroup> implements ExamGroupDao {
}
