package ru.akm.testtask.animals.jetty.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akm.testtask.animals.jetty.models.Animal;
import ru.akm.testtask.animals.jetty.models.AnimalType;
import ru.akm.testtask.animals.jetty.persist.AnimalRepository;
import ru.akm.testtask.animals.jetty.persist.AnimalTypeRepository;

/**
 * Сервис работы с животными
 * 
 * @author akm
 */
@Component
public class AnimalService {
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private AnimalRepository animalRepository;
   
    /**
     * Получение типов животных
     * 
     * @return Список типов животных
     */
    public ArrayList<AnimalType> getAnimalTypes() {
        return animalTypeRepository.getAnimalTypes();
    }
    
    /**
     * Получение информации о животном по ID
     * 
     * @param id    ID записи БД
     * @return      Полная информация о животном
     */
    public Animal getAnimal(Long id) {        
        return animalRepository.findOne(id);
    }
    
    /**
     * Получение списка всех животных в пределах пользователя
     * 
     * @return Список животных
     */
    public ArrayList<Animal> getAnimals() {
        return animalRepository.getAnimals();
    }
    
    /**
     * Сохранение описания животного в БД
     * 
     * @param a Объект "Животное"
     * @return Сохранённый объект "Животное"
     */
    public Animal setAnimal(Animal a) {
        return animalRepository.save(a);
    }
    
    /**
     * Удаление записи из БД
     * 
     * @param id ID записи
     */
    public void deleteAnimal(Long id) {
        animalRepository.delete(id);
    }
}
