package ru.akm.testtask.animals.jetty.persist;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.akm.testtask.animals.jetty.models.Animal;
import ru.akm.testtask.animals.jetty.models.User;
import ru.akm.testtask.animals.jetty.security.SecurityService;

/**
 *
 * @author akm
 */
public class AnimalRepositoryImpl implements AnimalRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserRepository userRepository;    

    @Override
    public ArrayList<Animal> getAnimals() {
        User u = userRepository.findUserByName(securityService.findLoggedInUsername());
        return new ArrayList<>(em.createNativeQuery("SELECT * FROM animals WHERE creator = :c ORDER BY caption", Animal.class).setParameter("c", u.getId()).getResultList());
    }

}
