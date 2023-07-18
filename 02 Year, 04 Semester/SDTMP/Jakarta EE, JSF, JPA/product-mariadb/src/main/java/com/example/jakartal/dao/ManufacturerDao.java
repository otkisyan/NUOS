package com.example.jakartal.dao;

import com.example.jakartal.entities.Manufacturer;
import com.example.jakartal.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Stateless
public class ManufacturerDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Manufacturer manufacturer) {

        em.persist(manufacturer);
        em.flush();

    }

    public List<Manufacturer> getAll() {

        return em.createNamedQuery("Manufacturer.findAll", Manufacturer.class).getResultList();
    }

    public Manufacturer findById(Long id) {

        TypedQuery<Manufacturer> query = em.createNamedQuery("Manufacturer.findById", Manufacturer.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public Manufacturer findByName(String name) {

        TypedQuery<Manufacturer> query = em.createNamedQuery("Manufacturer.findByName", Manufacturer.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    public Map<Manufacturer, Set<Product>> filterProductsByManufactures() {

        List<Manufacturer> manufacturers = getAll();
        Map<Manufacturer, Set<Product>> manufacturerProducts = new HashMap<>();

        for (Manufacturer m : manufacturers) {

            Set<Product> products = m.getProducts();
            manufacturerProducts.put(m, products);
        }

        return manufacturerProducts;

    }

}
