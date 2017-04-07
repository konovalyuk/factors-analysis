package com.intapp.tms.persistence.domain;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * The type Product.
 */
@Data
@NoArgsConstructor
public class Product implements Serializable {

    private String name;
    private String description;

    private Date validFrom;
    private Date validTo;
    private Map<String, Object> settings = Maps.newConcurrentMap();

    /**
     * Instantiates a new Product.
     *
     * @param name the name
     */
    public Product(String name) {
        this.name = name;
    }

}
