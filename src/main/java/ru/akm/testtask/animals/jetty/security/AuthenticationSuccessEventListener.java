package ru.akm.testtask.animals.jetty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.akm.testtask.animals.jetty.service.LoginAttemptService;

/**
 * Получатель события успешной аутентификации
 * 
 * @author akm
 */
@Component
public class AuthenticationSuccessEventListener 
  implements ApplicationListener<AuthenticationSuccessEvent> {
 
    @Autowired
    private LoginAttemptService loginAttemptService;
 
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        String userName = null;
        UserDetails auth = (UserDetails)e.getAuthentication().getDetails();
        if (auth == null) {
            userName = e.getAuthentication().getName();
        }
        else {
            userName = auth.getUsername();
        }
         
        loginAttemptService.loginSucceeded(userName);
    }
}