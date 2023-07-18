/*
package com.example.jakartal.converters;

import com.example.jakartal.beans.ManufacturerBean;
import com.example.jakartal.entities.Manufacturer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@FacesConverter(forClass = Manufacturer.class)
@Named
public class ManufacturerConverter implements Converter<Manufacturer> {

    @Override
    public Manufacturer getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            return manufacturerBean.findById(id);
        } catch (NumberFormatException e) {
            throw new ConverterException("Invalid value: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Manufacturer value) {
        if (value == null || value.getId() == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }

}
*/
