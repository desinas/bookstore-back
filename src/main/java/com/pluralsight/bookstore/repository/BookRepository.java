package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Transactional(SUPPORTS)
public class BookRepository {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager em;

    @Inject
    private NumberGenerator generator;

    @Inject
    private TextUtil textUtil;

    // ======================================
    // =          Business methods          =
    // ======================================

    public Book find(@NotNull Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(REQUIRED)
    public Book create(@NotNull Book book) {
        book.setIsbn(generator.generateNumber());
        book.setTitle(textUtil.sanitize(book.getTitle()));
        em.persist(book);
        return book;
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Book.class, id));
    }
}
