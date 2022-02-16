//package com.company.jcc.repository;
//
//import java.util.List;
//import com.company.jcc.model.Role;
//import com.company.jcc.model.User;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//
//@Repository
//public class RoleRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public Role create(Role role) {
//        entityManager.persist(role);
//        return role;
//    }
//
//    @Transactional
//    public Role readById(int id) {
//        Query query = entityManager.createQuery("from Role where id = " + id);
//        return (Role) query.getSingleResult();
//    }
//
//    @Transactional
//    public List<Role> findAll() {
//        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
//        return query.getResultList();
//    }
//
//    @Transactional
//    public Role update(Role role) {
//        return entityManager.merge(role);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        entityManager.remove(readById(id));
//    }
//
//
//}
