package ru.akm.testtask.animals.jetty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.akm.testtask.animals.jetty.models.User;

/**
 * Сервис базопасности
 * 
 * @author akm
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * Получение логина текущего пользователя
     * 
     * @return Логин
     */
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User)
        {
            return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserName();
        }
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Аутентификация сгенерированного пользователя
     * 
     * @param userName  Имя пользователя
     * @param userPwd   Пароль
     */
    @Override
    public void autologin(String userName, String userPwd) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userPwd, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(userDetails);
        
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {            
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }    
}
