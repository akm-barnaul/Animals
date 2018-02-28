package ru.akm.testtask.animals.jetty.persist;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.akm.testtask.animals.jetty.models.AnimalType;

/**
 *
 * @author akm
 */
public class AnimalTypeRepositoryImpl implements AnimalTypeRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public ArrayList<AnimalType> getAnimalTypes() {
        return new ArrayList<>(em.createNativeQuery("SELECT * FROM animal_types ORDER BY caption").getResultList());
    }  
}
