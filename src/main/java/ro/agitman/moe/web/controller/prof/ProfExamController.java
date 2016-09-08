package ro.agitman.moe.web.controller.prof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ItemTypeEnum;
import ro.agitman.moe.middle.service.ExamService;
import ro.agitman.moe.middle.service.ItemService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;
import ro.agitman.moe.web.dto.ExamEditDTO;
import ro.agitman.moe.web.dto.ExamItemDTO;

import java.util.List;

@Controller
@RequestMapping("/exam")
public class ProfExamController extends AbstractController {

    @Autowired
    ExamService examService;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;

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

        Exam exam = examService.findById(examId, getCurrentUser(userService));
        model.addAttribute("exam", exam);

        //todo add to the model the list of items from the exam
        model.addAttribute("items", itemService.findByExam(exam));

        model.addAttribute("item", new ExamItemDTO());
        model.addAttribute("itemTypes", ItemTypeEnum.values());

        return "/prof/editItem";
    }

    @RequestMapping(value = "editProperty", method = RequestMethod.POST)
    @ResponseBody
    public String edixExamProperty(ExamEditDTO data, ModelMap model) {

        if ("examName".equals(data.getName())) {
            examService.changeName(data);
        }
        if ("examDiff".equals(data.getName())) {
            examService.changeDifficulty(data);
        }
        return data.getValue();
    }
}
