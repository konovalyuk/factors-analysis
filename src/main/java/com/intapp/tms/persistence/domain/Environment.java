package com.intapp.tms.persistence.domain;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * The type Environment.
 */
@Data
@NoArgsConstructor
public class Environment implements Serializable {

    private String name;
    private String description;
    @ApiModelProperty(value = "Tenant Status")
    private EnumStatus status;
    private Date validFrom;
    private Date validTo;

    private Map<String, Object> settings = Maps.newConcurrentMap();

    /**
     * Instantiates a new Environment.
     *
     * @param name the name
     */
    public Environment(String name) {
        this.name = name;
    }

}
