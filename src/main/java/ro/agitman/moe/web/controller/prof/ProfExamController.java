package ro.agitman.moe.web.controller.prof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.service.ExamService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;

import java.util.List;

@Controller
@RequestMapping("/exam")
public class ProfExamController extends AbstractController {

    @Autowired
    ExamService examService;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String listExams(ModelMap model) {

        model.addAttribute("loggedinuser", getPrincipal());

        List<Exam> exams = examService.findAll(getCurrentUser(userService));
        model.addAttribute("exams", exams);

        return "/prof/listExams";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createExam(Exam exam) {

        examService.createExam(exam, getCurrentUser(userService));

        return "redirect:/exam";
    }

    @RequestMapping(value = {"/edit-{examId}"}, method = RequestMethod.GET)
    public String editExam(@PathVariable Integer examId, ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());

        model.addAttribute("exam", examService.findById(examId, getCurrentUser(userService)));

        return "/prof/editItem";
    }


}
