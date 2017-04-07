package com.intapp.tms.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * The type Product dto.
 */
@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validTo;

    private Map<String, Object> settings = Maps.newConcurrentMap();

    /**
     * Instantiates a new Product dto.
     *
     * @param name the name
     */
    public ProductDTO(String name) {
        this.name = name;
    }

}
