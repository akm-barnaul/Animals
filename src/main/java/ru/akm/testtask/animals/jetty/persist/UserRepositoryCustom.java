package ru.akm.testtask.animals.jetty.persist;

import ru.akm.testtask.animals.jetty.models.User;

/**
 *
 * @author akm
 */
public interface UserRepositoryCustom {
    User createUser();
    User findUserByName(String name);
}
