package ro.agitman.moe.web.controller.prof;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.agitman.moe.middle.service.ItemService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;
import ro.agitman.moe.web.dto.ExamItemDTO;
import ro.agitman.moe.web.dto.ItemAnswerDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ProfItemController extends AbstractController {

    public static final String ITEM_ID = "examItemId";

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;



    @RequestMapping(method = RequestMethod.POST)
    public String saveItem(ExamItemDTO examItem, HttpServletRequest request) {

        itemService.createItem(examItem);

        request.getSession().setAttribute(ITEM_ID, examItem.getId());

        return "redirect:/exam/edit-" + examItem.getExamId();
    }

    @RequestMapping(value="edit-{id}-{eid}", method = RequestMethod.GET)
    public String editItem(@PathVariable("id") Integer itemId, @PathVariable("eid") Integer examId, HttpServletRequest request) {

        request.getSession().setAttribute(ITEM_ID, itemId);

        return "redirect:/exam/edit-" + examId;
    }

    @RequestMapping(value="clean-{eid}", method = RequestMethod.GET)
    public String cleanItem(@PathVariable("eid") Integer examId, HttpServletRequest request) {

        request.getSession().removeAttribute(ITEM_ID);

        return "redirect:/exam/edit-" + examId;
    }

    @RequestMapping(value="remove-{id}-{eid}", method = RequestMethod.GET)
    public String removeItem(@PathVariable("id") Integer itemId, @PathVariable("eid") Integer examId, HttpServletRequest request) {

        itemService.remove(itemId);

        request.getSession().removeAttribute(ITEM_ID);

        return "redirect:/exam/edit-" + examId;
    }

// -----------------------------------------------------------------
// ---------------- ANSWERS ACTIONS --------------------------------

    @RequestMapping(value="as-{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getAnswers(@PathVariable("id") Integer itemId) {
        List<ItemAnswerDTO> result = new ArrayList<>();

        ItemAnswerDTO i = new ItemAnswerDTO();
        i.setCorrect(false);
        i.setValue("test");

        result.add(i);

        Gson gson = new Gson();

        return gson.toJson(result);
    }
}
