package ru.akm.testtask.animals.jetty.security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akm.testtask.animals.jetty.models.User;
import ru.akm.testtask.animals.jetty.persist.UserRepository;
import ru.akm.testtask.animals.jetty.service.LoginAttemptService;

/**
 * Сервис подробных сведений о пользователе
 * 
 * @author akm
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(userName)) {
            throw new RuntimeException("Число попыток истекло");
        }
        
        User user = userRepository.findUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь "+userName+" не найден");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPwd(), grantedAuthorities);
    }
}
