package ru.akm.testtask.animals.jetty.persist;

import org.springframework.data.repository.Repository;
import ru.akm.testtask.animals.jetty.models.AnimalType;

/**
 *
 * @author akm
 */
public interface AnimalTypeRepository extends Repository<AnimalType, Long>, AnimalTypeRepositoryCustom 
{
    
}
