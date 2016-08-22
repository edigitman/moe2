package ro.agitman.moe.middle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.ExamDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.service.ExamService;

import java.util.List;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamDao examDao;

    @Override
    public List<Exam> findLatestFive() {
        return examDao.findRecentCount(5);
    }
}
