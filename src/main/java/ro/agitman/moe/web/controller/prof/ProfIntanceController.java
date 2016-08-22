package ro.agitman.moe.web.controller.prof;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.agitman.moe.web.controller.AbstractController;

@Controller
@RequestMapping("/instance")
public class ProfIntanceController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        model.addAttribute("loggedinuser", getPrincipal());
//        TODO fill in default data

        return "/prof/listInstances";
    }

}
