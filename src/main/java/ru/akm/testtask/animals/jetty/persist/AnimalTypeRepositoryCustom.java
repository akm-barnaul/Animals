package ru.akm.testtask.animals.jetty.persist;

import java.util.ArrayList;
import ru.akm.testtask.animals.jetty.models.AnimalType;

/**
 *
 * @author akm
 */
public interface AnimalTypeRepositoryCustom {
    ArrayList<AnimalType> getAnimalTypes();
}
