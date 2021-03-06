package ro.agitman.moe.middle.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Created by d-uu31cq on 19.08.2016.
 */
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
 
    @Autowired
    @Qualifier("tokenRepositoryDao")
    PersistentTokenRepository tokenRepository;
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").access("hasRole('PROFESSOR') or hasRole('ADMIN') or hasRole('STUDENT')")
//                .antMatchers("/", "/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//                .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
//                .antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")

                .antMatchers("/admin*").access("hasRole('ADMIN')")

                .antMatchers("/stud*").access("hasRole('STUDENT')")

                .antMatchers("/exam*").access("hasRole('PROFESSOR')")
                .antMatchers("/group*").access("hasRole('PROFESSOR')")
                .antMatchers("/instance*").access("hasRole('PROFESSOR')")
                .antMatchers("/stats*").access("hasRole('PROFESSOR')")
                .antMatchers("/item*").access("hasRole('PROFESSOR')")
                .antMatchers("/review*").access("hasRole('PROFESSOR')")

                .and().formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("ssoId")
                .passwordParameter("password").and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400).and()
                .csrf().and()
                .exceptionHandling().accessDeniedPage("/Access_Denied");
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
 
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }
 
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
 
}