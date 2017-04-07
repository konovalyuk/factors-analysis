package com.intapp.tms.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * The type Environment dto.
 */
@Data
@NoArgsConstructor
public class EnvironmentDTO implements Serializable {

    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validTo;

    private Map<String, Object> settings = Maps.newConcurrentMap();

    /**
     * Instantiates a new Environment dto.
     *
     * @param name the name
     */
    public EnvironmentDTO(String name) {
        this.name = name;
    }

}
