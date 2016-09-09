package ro.agitman.moe.web.controller.prof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.agitman.moe.middle.service.ItemService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;
import ro.agitman.moe.web.dto.ExamItemDTO;

import javax.servlet.http.HttpServletRequest;

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
}
