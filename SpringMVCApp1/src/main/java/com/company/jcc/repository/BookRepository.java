package com.company.jcc.repository;

import com.company.jcc.model.Book;
import com.company.jcc.model.Rent;
import com.company.jcc.service.BookService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional
    public List<Book> getAll(){
        TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
        return query.getResultList();
    }

    @Transactional
    public Book readById(int id) {
        Query query = entityManager.createQuery("from Book where id = " + id);
        return (Book) query.getSingleResult();
    }

    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Transactional
    public void delete(int id) {
        Query query =  entityManager.createQuery("delete from Book where id = " + id);
        entityManager.remove(readById(id));
    }

    @Transactional
    public void rentBook(int id){
        Query query = entityManager.createQuery("update Book \n" +
                " set amountLeft = amountLeft - 1, amountGave = amountGave + 1\n where id = " + id);
//        query.setParameter("amountLeft", book.getAmountLeft() - 1);
//        query.setParameter("amountGave", book.getAmountGave() + 1);
        query.executeUpdate();
    }

    @Transactional
    public List<Rent> averageTime(int id){
//        Query query = entityManager.createQuery("select function('DATEDIFF',timeReturned, timeTaken) from  Rent where book.id = " + id, Rent.class);
//        Query query = entityManager.createNativeQuery("select round(avg(DATEDIFF(time_returned, time_taken))) as AvgTimeReading, amount_left, amount_gave from rent r \n" +
//                "JOIN book b ON r.book_id = b.id");
        Query query = entityManager.createQuery("from Rent where timeReturned is not null and book.id = " + id);
        return query.getResultList();
    }

    public BookRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
