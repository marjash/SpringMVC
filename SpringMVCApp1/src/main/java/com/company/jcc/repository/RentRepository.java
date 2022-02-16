package com.company.jcc.repository;

import com.company.jcc.model.Rent;
import com.company.jcc.model.User;
import org.hibernate.Criteria;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Rent create(Rent rent) {
        entityManager.persist(rent);
        return rent;
    }

    @Transactional
    public Rent readById(int id) {
        Query query = entityManager.createQuery("from Rent where id = " + id);
        return (Rent) query.getSingleResult();
    }

    @Transactional
    public List<Rent> findAll() {
        TypedQuery<Rent> query = entityManager.createQuery("from Rent", Rent.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(readById(id));
    }

    @Transactional
    public Rent update(Rent rent){
        return entityManager.merge(rent);
    }

    @Transactional
    public Rent getMostPopular(LocalDate start, LocalDate end){
        Query query = entityManager.createQuery("from Rent where timeTaken between " + "'" + start + "'" + " and " + "'" + end + "'" + " group by book.bookTitle order by count(book.bookTitle) DESC");
        return (Rent) query.setMaxResults(1).getSingleResult();
    }

    @Transactional
    public Rent getMostUnPopular(LocalDate start, LocalDate end){
        Query query = entityManager.createQuery("from Rent where timeTaken between " + "'" + start + "'" + " and " + "'" + end + "'" + " group by book.bookTitle order by count(book.bookTitle) ASC");
        return (Rent) query.setMaxResults(1).getSingleResult();
    }

    @Transactional
    public List<User> findUsersNotReturnedInTime(){
//        TypedQuery<Rent> query = entityManager.createQuery("select distinct r from Rent r left join fetch r.user where r.timeReturned > r.timeShouldBeReturned or r.timeReturned = null and r.timeShouldBeReturned > current_date", Rent.class).setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        TypedQuery<User> s = entityManager.createQuery("select distinct r.user from Rent r", User.class);
        return s.getResultList();
    }

    @Transactional
    public int howManyBook(LocalDate start, LocalDate end){
        Query query = entityManager.createQuery("from Rent where timeTaken between " + "'" + start + "'" + " and " + "'" + end + "'");
        return query.getResultList().size();
    }

    @Transactional
    public List<Rent> hasRead(int id){
        Query query = entityManager.createQuery("from Rent where user.id = " + id);
        return query.getResultList();
    }

    @Transactional
    public void backBook(int id){
        Query query = entityManager.createQuery("update Book \n" +
                " set amountLeft = amountLeft + 1, amountGave = amountGave - 1  where id = " + id);
        query.executeUpdate();
    }
}
