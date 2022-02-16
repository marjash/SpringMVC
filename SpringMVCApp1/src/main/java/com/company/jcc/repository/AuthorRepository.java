package com.company.jcc.repository;

import com.company.jcc.model.Author;
import com.company.jcc.model.Book;
import com.company.jcc.service.AuthorService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Transactional
    public int readByName(String title) {
        Query query = entityManager.createQuery("select id from Author where authorSurname = " + "'" + title + "'");
        return (int) query.getSingleResult();


    }
    @Transactional
    public List<Author> getAll(){
        TypedQuery<Author> query = entityManager.createQuery("from Author", Author.class);
        return query.getResultList();
    }

    @Transactional
    public Author readById(int id) {
        Query query = entityManager.createQuery("from Author where id = " + id);
        return (Author) query.getSingleResult();
    }

    @Transactional
    public Author update(Author author) {
        return null;
    }

    @Transactional
    public void delete(int id) {
        Query query =  entityManager.createQuery("delete from Author where id = " + id);
        entityManager.remove(readById(id));
    }

    public AuthorRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
