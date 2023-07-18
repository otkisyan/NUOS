package com.example.jakartal.beans;

import com.example.jakartal.dao.ManufacturerDao;
import com.example.jakartal.entities.Manufacturer;
import com.example.jakartal.entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class ManufacturerBean implements Serializable {

    @EJB
    private ManufacturerDao manufacturerDao;

    private Manufacturer manufacturer;

    @PostConstruct
    public void init() {

        manufacturer = new Manufacturer();

    }

    public void add(Manufacturer manufacturer) {

        manufacturerDao.add(manufacturer);
    }


    public List<Manufacturer> getManufacturers() {

        return manufacturerDao.getAll();
    }

    private Manufacturer getManufacturer() {

        return manufacturer;
    }

    public Manufacturer findById(Long id) {

        return manufacturerDao.findById(id);
    }

    public Manufacturer findByName(String name) {

        return manufacturerDao.findByName(name);
    }

    public Map<Manufacturer, Set<Product>> filterProductsByManufactures() {

        return manufacturerDao.filterProductsByManufactures();

    }

}
