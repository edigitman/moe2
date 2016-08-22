package ro.agitman.moe.web.controller;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    protected boolean isCurrentAuthenticationAnonymous(AuthenticationTrustResolver authenticationTrustResolver) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    protected boolean isCurrentAuthenticationAdmin(AuthenticationTrustResolver authenticationTrustResolver) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            for(GrantedAuthority ga : authentication.getAuthorities()){
                if("ROLE_ADMIN".equals(ga.getAuthority()))
                    return true;
            }
        }

        return false;
    }

    protected boolean isCurrentAuthenticationProfessor(AuthenticationTrustResolver authenticationTrustResolver) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    protected boolean isCurrentAuthenticationStudent(AuthenticationTrustResolver authenticationTrustResolver) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
