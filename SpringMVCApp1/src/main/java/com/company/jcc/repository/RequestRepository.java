package com.company.jcc.repository;

import com.company.jcc.model.Rent;
import com.company.jcc.model.Request;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Request create(Request request) {
        entityManager.persist(request);
        return request;
    }

    @Transactional
    public Request readById(int id) {
        Query query = entityManager.createQuery("from Request where id = " + id);
        return (Request) query.getSingleResult();
    }

    @Transactional
    public List<Request> findAll() {
        TypedQuery<Request> query = entityManager.createQuery("from Request", Request.class);
        return query.getResultList();
    }

    @Transactional
    public List<Request> findAllByUserId(int id) {
        TypedQuery<Request> query = entityManager.createQuery("from Request where user.id = " + id, Request.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(readById(id));
    }

    @Transactional
    public Request update(Request request) {
        return entityManager.merge(request);
    }

    @Transactional
    public long avgRequest(LocalDate start, LocalDate end){
        Query query = entityManager.createQuery("select (count(id)) / (count(distinct user.id)) from Request where time between " + "'" + start + "'" + " and " + "'" + end + "'");
        return (long) query.getSingleResult();
    }
}
