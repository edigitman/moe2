package ro.agitman.moe.middle.dao.impl;

import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.StudentItemAnswerDao;
import ro.agitman.moe.middle.model.StudentItemAnswer;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
@Repository("studentItemAnswerDao")
public class StudentItemAnswerDaoImpl extends AbstractDao<Integer, StudentItemAnswer> implements StudentItemAnswerDao {

}
