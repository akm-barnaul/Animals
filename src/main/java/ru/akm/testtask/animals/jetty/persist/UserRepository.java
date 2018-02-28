package ru.akm.testtask.animals.jetty.persist;

import org.springframework.data.repository.CrudRepository;
import ru.akm.testtask.animals.jetty.models.User;
/**
 *
 * @author akm
 */
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    
}
