package ro.agitman.moe.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.UserService;

import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    UserService userService;
    @Autowired
    MessageSource messageSource;

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (user.getId() == null) {
            user.setSsoId(user.getEmail());
            user.setPassword(UUID.randomUUID().toString());
        }

        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "email", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }


        userService.saveUser(user);

//        model.addAttribute("success", "Utilizatorul <b>" + user.getFirstName() + " " + user.getLastName() + "</b> a fost creat cu succes");
//        model.addAttribute("loggedinuser", getPrincipal());

        return "redirect:/home";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.updateUser(user);

        return "redirect:/home";
    }
}
