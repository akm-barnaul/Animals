package ru.akm.testtask.animals.jetty.persist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import ru.akm.testtask.animals.jetty.models.User;

/**
 *
 * @author akm
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;
    
    @Transactional
    @Override
    public User createUser() {
        User u = new User();
        u.setUserName(Long.toString(Double.doubleToLongBits(Math.random())).substring(0, 5));

        Query q = em.createNativeQuery("SELECT * FROM APP_USERS WHERE user_name = :u_name", User.class);
        while (q.setParameter("u_name", u.getUserName())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null) != null) {
            u.setUserName(Long.toString(Double.doubleToLongBits(Math.random())).substring(0, 5));
        }
        String pwd = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0,7);
        u.setUserPwd(passwordEncoder.encode(pwd));        
        em.persist(u);
        return new User(u.getId(), u.getUserName(), pwd);
    }

    @Override
    public User findUserByName(String name) {
        return (User)em.createNativeQuery("SELECT * FROM APP_USERS WHERE user_name = :u_name", User.class)
                .setParameter("u_name", name)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }
}
