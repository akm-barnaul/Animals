package ru.akm.testtask.animals.jetty.persist;

import java.util.ArrayList;
import ru.akm.testtask.animals.jetty.models.Animal;

/**
 *
 * @author akm
 */
public interface AnimalRepositoryCustom {
    ArrayList<Animal> getAnimals();
}
