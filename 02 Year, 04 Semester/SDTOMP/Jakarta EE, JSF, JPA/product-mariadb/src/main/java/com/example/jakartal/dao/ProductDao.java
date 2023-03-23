package com.example.jakartal.dao;

import com.example.jakartal.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ProductDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Product product) {

        em.persist(product);
        em.flush();
    }

    public void delete(Long id) {

        Query query = em.createNamedQuery("Product.deleteById");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    public List<Product> getProducts() {

        return em.createNamedQuery("Product.findAll", Product.class).getResultList();

    }

    // A - Список товарів для заданого найменування в порядку спадання терміну зберігання
    public List<Product> findByNameOrderByExpirationDateDesc(String name) {

        TypedQuery<Product> query = em.createNamedQuery("Product.findByNameOrderByExpirationDateDesc", Product.class);
        query.setParameter("name", name);

        return query.getResultList();

    }

    // B - Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> findByNameAndPriceLessThanEqual(String name, double price) {

        TypedQuery<Product> query = em.createNamedQuery("Product.findByNameAndPriceLessThanEqual", Product.class);
        query.setParameter("name", name);
        query.setParameter("price", price);

        return query.getResultList();

    }

    // C - Список товарів, термін зберігання яких більше заданого
    public List<Product> findByExpirationDateGreaterThan(LocalDate expirationDate) {

        TypedQuery<Product> query = em.createNamedQuery("Product.findByExpirationDateGreaterThan", Product.class);
        query.setParameter("expirationDate", expirationDate);

        return query.getResultList();

    }

    // D - Список товарів, впорядкований за зростанням вартості (кількість * ціна),
    // якщо вартість однакова, то за спаданням ціни;
    public List<Product> sortListByPriceAscending() {

        Query query = em.createNativeQuery("SELECT * FROM product ORDER BY amount * price ASC, price DESC", Product.class);
        return query.getResultList();

    }

}
