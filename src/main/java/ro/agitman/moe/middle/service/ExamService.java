package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.Exam;

import java.util.List;

public interface ExamService {


    List<Exam> findLatestFive();

}
