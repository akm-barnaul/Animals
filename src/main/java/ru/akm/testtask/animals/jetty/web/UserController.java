package ru.akm.testtask.animals.jetty.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.akm.testtask.animals.jetty.models.User;
import ru.akm.testtask.animals.jetty.security.SecurityService;
import ru.akm.testtask.animals.jetty.service.UserService;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping("/user/new")
    @ResponseBody
    public User getNewUser() {
        User u = userService.getNewUser();
        try {
            securityService.autologin(u.getUserName(), u.getUserPwd());
            return u;
        }
        catch (Exception e) {
            return null;
        }
    }    
    
    @RequestMapping("/user/i")
    @ResponseBody
    public User getCurrentUser() {
        return userService.getUser(securityService.findLoggedInUsername());
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUser(@RequestBody User u) {
        return (userService.getUser(u.getUserName())== null ? "{\"message\":\"Имя свободно\"}" : "{\"message\":\"Имя занято\"}");
    }
}
