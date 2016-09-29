package ro.agitman.moe.web.controller.prof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.ExamGroupService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.controller.AbstractController;
import ro.agitman.moe.web.dto.ExamGroupDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/group")
public class ProfGroupController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExamGroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllGroups(ModelMap model) {

        User user = getCurrentUser(userService);

        model.addAttribute("groups", groupService.findAll(user));

        return "/prof/listGroups";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveGroup(ExamGroupDTO group, ModelMap model) {

        User user = getCurrentUser(userService);

        groupService.createGroup(user, group);

        return "redirect:/group";
    }


    @RequestMapping(value = "remove-{id}", method = RequestMethod.GET)
    public String removeGroup(@PathVariable("id") Integer itemId) {

        groupService.remove(itemId);


        return "redirect:/group";
    }


    @RequestMapping(value = "studs-{id}", method = RequestMethod.POST)
    @ResponseBody
    public String addGroups(@PathVariable("id") Integer groupId, List<Integer> studs) {

        User user = getCurrentUser(userService);

        groupService.addStudents(groupId, studs);

        return "200";
    }

}
