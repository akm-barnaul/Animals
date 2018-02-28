package ru.akm.testtask.animals.jetty.persist;

import org.springframework.data.repository.CrudRepository;
import ru.akm.testtask.animals.jetty.models.Animal;

/**
 *
 * @author akm
 */
public interface AnimalRepository extends CrudRepository<Animal, Long>, AnimalRepositoryCustom{
    
}
