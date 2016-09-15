package ro.agitman.moe.web.controller.prof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.agitman.moe.middle.dao.ItemDao;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.ExamItem;
import ro.agitman.moe.middle.model.ItemTypeEnum;
import ro.agitman.moe.middle.service.ExamService;
import ro.agitman.moe.middle.service.ItemService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;
import ro.agitman.moe.web.dto.ExamEditDTO;
import ro.agitman.moe.web.dto.ExamItemDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public String editExam(@PathVariable Integer examId, HttpServletRequest request, ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());

        Exam exam = examService.findById(examId, getCurrentUser(userService));
        model.addAttribute("exam", exam);

        ExamItemDTO examItemDTO = getNewOrEdit(request);
        model.addAttribute("item", examItemDTO);

        model.addAttribute("items", loadItems(exam, examItemDTO));

        model.addAttribute("itemTypes", ItemTypeEnum.values());

        return "/prof/editItem";
    }

    @RequestMapping(value = "editProperty", method = RequestMethod.POST)
    @ResponseBody
    public String editExamProperty(ExamEditDTO data, ModelMap model) {

        if ("examName".equals(data.getName())) {
            examService.changeName(data);
        }
        if ("examDiff".equals(data.getName())) {
            examService.changeDifficulty(data);
        }
        return data.getValue();
    }

    private List<ExamItemDTO> loadItems(Exam exam, ExamItemDTO itemDTO){

        final Integer[] points = {0};

        List<ExamItem> items = itemService.findByExam(exam);
        List<ExamItemDTO> itemsDTO = new ArrayList<>(items.size());

        items.forEach(ex -> { points[0] += ex.getPoints(); itemsDTO.add(new ExamItemDTO(ex, ex.getId().equals(itemDTO.getId()))); });

        exam.setPoints(points[0]);

        return itemsDTO;
    }

    private ExamItemDTO getNewOrEdit(HttpServletRequest request) {
        Object sessionItemId = request.getSession().getAttribute(ProfItemController.ITEM_ID);

        if (sessionItemId != null && sessionItemId instanceof Integer) {
            return new ExamItemDTO(itemService.getByKey((Integer) sessionItemId));
        }

        return new ExamItemDTO();
    }
}
