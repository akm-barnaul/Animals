package ru.akm.testtask.animals.jetty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akm.testtask.animals.jetty.models.User;
import ru.akm.testtask.animals.jetty.persist.UserRepository;
import ru.akm.testtask.animals.jetty.security.SecurityService;

/**
 * Сервис для работы с пользователями
 * 
 * @author akm
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityService securityService;
    
    /**
     * Создание нового пользователя
     * 
     * @return Новый пльзователь сервиса
     */
    public User getNewUser() {
        return userRepository.createUser();
    }
    
    /**
     * Получение пользователя по логину
     * 
     * @param userName  Логин пользователя
     * @return  Пользователь
     */
    public User getUser(String userName) {
        return userRepository.findUserByName(userName);
    }
    
    /**
     * Получение активного пользователя сессии
     * 
     * @return Пользователь
     */
    public User getCurrentUser() {
        return userRepository.findUserByName(securityService.findLoggedInUsername());
    }
}
