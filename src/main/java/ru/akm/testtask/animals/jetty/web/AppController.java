package ru.akm.testtask.animals.jetty.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Базовый контроллер сервиса
 * 
 * @author akm
 */
@RestController
public class AppController {
    /**
     * Информация о сервисе
     * 
     * @return 
     */
    @RequestMapping("/")
    @ResponseBody
    public String getIndex() {
        return "{\"app\":\"Animals 0.1-SNAPSHOT\"}";
    }        
}
