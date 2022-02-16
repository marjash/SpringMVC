package com.company.jcc.repository;

import com.company.jcc.model.Rent;
import com.company.jcc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User readById(int id) {
        Query query = entityManager.createQuery("from User where id = " + id);
        return (User) query.getSingleResult();
    }

    @Transactional
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(readById(id));
    }

    @Transactional
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("from User where username = " + "'" + email + "'");
        return (User) query.getSingleResult();
    }

    @Transactional
    public int avgAge(){
        Query query = entityManager.createQuery("select cast(avg(age) as integer)  from User");
        return (int) query.getSingleResult();
    }

//

}
