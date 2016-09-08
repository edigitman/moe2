package ro.agitman.moe.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ro.agitman.moe.middle.model.Exam;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.model.UserProfile;
import ro.agitman.moe.middle.service.ExamService;
import ro.agitman.moe.middle.service.UserProfileService;
import ro.agitman.moe.middle.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController extends  AbstractController{

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    ExamService examService;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        return "redirect:/home";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String displayHome(ModelMap model) {

        model.addAttribute("loggedinuser", getPrincipal());

        if(isCurrentAuthenticationAdmin(authenticationTrustResolver)){
            List<User> users = userService.findAllUsersWithRoles();
            model.addAttribute("users", users);
        }

        if(isCurrentAuthenticationProfessor(authenticationTrustResolver)){
            List<Exam> exams = examService.findLatestFive(getCurrentUser(userService));
            model.addAttribute("exams", exams);
        }

        return "home";
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous(authenticationTrustResolver)) {
            return "login";
        } else {
            return "redirect:/home";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
}