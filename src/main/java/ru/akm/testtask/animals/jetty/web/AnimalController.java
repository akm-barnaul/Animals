package ru.akm.testtask.animals.jetty.web;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RestController
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private UserService userService;
    
    /**
     * Получение печерня типов животных
     * 
     * @return Перечень типов животных
     */
    @RequestMapping(path = "/animal/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<AnimalType> getTypes() {
        return animalService.getAnimalTypes();
    }
    
    /**
     * Получение информации об 1 животном по ID
     * 
     * @param animal {"id":<идентификатор животного>}
     * @return Информация о животном
     */
    @RequestMapping(path = "/animal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal getAnimal(@RequestBody Animal animal) {
        return animalService.getAnimal(animal.getId());
    }
    
    /**
     * Получение информации об 1 животном по ID
     * 
     * @param id    Идентификатор записи животного в БД
     * @return      Информация о животном
     */
    @RequestMapping(path = "/animal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal getAnimalByID(@PathVariable Long id) {
        return animalService.getAnimal(id);
    }
    
    /**
     * Получение всех животных, созданных пользователем
     * 
     * @return Список животных
     */
    @RequestMapping(path = "/animals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Animal> getAnimal() {
        return animalService.getAnimals();
    }
    
    /**
     * Создание новой записи о животном
     * 
     * @param animal    Объект в JSON-формате
     * @return          Созданный объект "Животное"
     */
    @RequestMapping(path = "/animal/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal setNewAnimal(@RequestBody Animal animal) {
        animal.setCreator(userService.getCurrentUser().getId());
        animal.setId(null);
        return animalService.setAnimal(animal);
    }
    
    /**
     * Обновление информации о животном
     * 
     * @param animal    Объект в JSON-формате
     * @return          Изменённый объект
     */
    @RequestMapping(path = "/animal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Animal setAnimal(@RequestBody Animal animal) {
        animal.setCreator(animalService.getAnimal(animal.getId()).getCreator());
        return animalService.setAnimal(animal);
    }
    
    /**
     * Удаление записи животного по ID
     * 
     * @param animal    Объект в JSON-формате (достаточно  {"id":<идентификатор животного>})
     */
    @RequestMapping(path = "/animal", method = RequestMethod.DELETE)
    public void deleteAnimal (@RequestBody Animal animal) {
        animalService.deleteAnimal(animal.getId());
    }
    
    /**
     * Удаление записи животного по ID
     * 
     * @param id    Идентификатор записи зивотного
     */
    @RequestMapping(path = "/animal/{id}", method = RequestMethod.DELETE)
    public void deleteAnimalByID (@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
