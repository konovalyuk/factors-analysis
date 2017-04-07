package com.intapp.tms.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * The type Tenant.
 */
@Data
@NoArgsConstructor
@Document(collection = "tenants")
public class Tenant implements Serializable {

    @Id
    private String id;
    private String name;
    private Address address;

    private TimeZone timeZone;
    private Locale defaultLocale;
    private Locale locale;

    private Map<String, Environment> environments;
    private Map<String, Product> products;

    private String description;

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    /**
     * Instantiates a new Tenant.
     *
     * @param name the name
     */
    public Tenant(String name) {
        this.name = name;
    }

}
