//package com.company.jcc.repository;
//
//import com.company.jcc.model.RegUser;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//
//@Repository
//public class RegUserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public RegUser findByUserName(String username) {
//        Query query = entityManager.createQuery("from RegUser where username = " + "'" + username + "'");
//        return (RegUser) query.getSingleResult();
//    }
//
//    @Transactional
//    public void create(RegUser regUser) {
//        entityManager.persist(regUser);
//    }
//}
