package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.User;

import java.util.List;

public interface ExamService {


    List<Exam> findLatestFive(User user);

    List<Exam> findAll(User user);

    void createExam(Exam exam, User user);

    Exam findById(Integer examId, User user);
}
