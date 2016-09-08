package ro.agitman.moe.web.controller;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.UserService;

/**
 * Created by edi on 8/19/16.
 */
public class AbstractController {

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    protected String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    protected User getCurrentUser(UserService userService) {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userService.findBySSO(userName);
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    protected boolean isCurrentAuthenticationAnonymous(AuthenticationTrustResolver authenticationTrustResolver) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    protected boolean isCurrentAuthenticationAdmin(AuthenticationTrustResolver authenticationTrustResolver) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if ("ROLE_ADMIN".equals(ga.getAuthority()))
                    return true;
            }
        }

        return false;
    }

    protected boolean isCurrentAuthenticationProfessor(AuthenticationTrustResolver authenticationTrustResolver) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if ("ROLE_PROFESSOR".equals(ga.getAuthority()))
                    return true;
            }
        }

        return false;
    }

    protected boolean isCurrentAuthenticationStudent(AuthenticationTrustResolver authenticationTrustResolver) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if ("ROLE_STUDENT".equals(ga.getAuthority()))
                    return true;
            }
        }

        return false;
    }
}
