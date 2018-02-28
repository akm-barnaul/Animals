package ru.akm.testtask.animals.jetty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.akm.testtask.animals.jetty.service.LoginAttemptService;

/**
 *
 * @author akm
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
 
    @Autowired
    private LoginAttemptService loginAttemptService;
 
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String userName = null;
        UserDetails auth = (UserDetails)e.getAuthentication().getDetails();
        if (auth == null) {
            userName = e.getAuthentication().getName();
        }
        else {
            userName = auth.getUsername();
        }
         
        loginAttemptService.loginFailed(userName);
    }
}
