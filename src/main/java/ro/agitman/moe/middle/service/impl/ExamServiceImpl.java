package ro.agitman.moe.middle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.ExamDao;
import ro.agitman.moe.middle.model.DifficultyEnum;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.ExamService;
import ro.agitman.moe.web.dto.ExamEditDTO;

import java.util.Date;
import java.util.List;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamDao examDao;

    @Override
    public List<Exam> findLatestFive(User user) {
        return examDao.findRecentCount(5, user);
    }

    @Override
    public List<Exam> findAll(User user) {
        return examDao.findAll(user);
    }

    @Override
    public Exam findById(Integer examId, User user) {
        Exam exam = examDao.getByKey(examId);

        if (!exam.getOwner().getId().equals(user.getId())) {
            throw new IllegalStateException("Access denied !");
        }

        return exam;
    }

    @Override
    public void createExam(Exam exam, User user) {
        exam.setOwner(user);
        exam.setItems(0);
        exam.setPoints(0);
        exam.setDatecreated(new Date());
        exam.setLocked(false);

        examDao.persist(exam);
    }

    @Override
    public void changeName(ExamEditDTO data) {
        Integer examId = data.getPk();
        String newName = data.getValue();

        Exam exam = examDao.getByKey(examId);
        exam.setName(newName);
        examDao.update(exam);
    }

    @Override
    public void changeDifficulty(ExamEditDTO data) {
        Integer examId = data.getPk();
        String newDifficulty = data.getValue();

        Exam exam = examDao.getByKey(examId);
        exam.setDifficulty(DifficultyEnum.valueOf(newDifficulty));
        examDao.update(exam);
    }
}
