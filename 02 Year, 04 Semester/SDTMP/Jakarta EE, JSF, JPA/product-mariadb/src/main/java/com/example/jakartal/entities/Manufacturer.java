package com.example.jakartal.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "manufacturer")
@NamedQueries({
        @NamedQuery(name = "Manufacturer.findByName", query = "select m from Manufacturer m where m.name = :name"),
        @NamedQuery(name = "Manufacturer.findAll", query = "select m from Manufacturer m"),
        @NamedQuery(name = "Manufacturer.findById", query = "select m from Manufacturer m where m.id = :id")
})
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer", orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}