package ru.akm.testtask.animals.jetty.web;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akm.testtask.animals.jetty.models.Animal;
import ru.akm.testtask.animals.jetty.models.AnimalType;
import ru.akm.testtask.animals.jetty.service.AnimalService;
import ru.akm.testtask.animals.jetty.service.UserService;

/**
 * @author akm
 * 
 * Контроллер работы с животными
 * 
 */
@Controller
@RestController
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(path = "/animal/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<AnimalType> getTypes() {
        return animalService.getAnimalTypes();
    }
    
    @RequestMapping(path = "/animal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal getAnimal(@RequestBody Animal animal) {
        return animalService.getAnimal(animal.getId());
    }
    
    @RequestMapping(path = "/animal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal getAnimalByID(@PathVariable Long id) {
        return animalService.getAnimal(id);
    }
    
    @RequestMapping(path = "/animals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Animal> getAnimal() {
        return animalService.getAnimals();
    }
    
    @RequestMapping(path = "/animal/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal setNewAnimal(@RequestBody Animal animal) {
        animal.setCreator(userService.getCurrentUser().getId());
        animal.setId(null);
        return animalService.setAnimal(animal);
    }
    
    @RequestMapping(path = "/animal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal setAnimal(@RequestBody Animal animal) {
        animal.setCreator(animalService.getAnimal(animal.getId()).getCreator());
        return animalService.setAnimal(animal);
    }
    
    @RequestMapping(path = "/animal", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAnimal (@RequestBody Animal animal) {
        animalService.deleteAnimal(animal.getId());
        return "";
    }
    
    @RequestMapping(path = "/animal/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAnimalByID (@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return "";
    }
}
