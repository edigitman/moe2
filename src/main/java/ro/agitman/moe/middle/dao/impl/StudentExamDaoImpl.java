package ro.agitman.moe.middle.dao.impl;

import org.springframework.stereotype.Repository;
import ro.agitman.moe.middle.dao.AbstractDao;
import ro.agitman.moe.middle.dao.StudentExamDao;
import ro.agitman.moe.middle.model.StudentExamInstance;

/**
 * Created by d-uu31cq on 16.09.2016.
 */
@Repository("studentExamDao")
public class StudentExamDaoImpl extends AbstractDao<Integer, StudentExamInstance> implements StudentExamDao {
}
