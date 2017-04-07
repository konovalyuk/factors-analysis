package com.intapp.tms.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * The type Tenant dto.
 */
@Data
@NoArgsConstructor
@Document(collection = "tenants")
public class TenantDTOPost implements Serializable {

    @NotNull
    @Size(min=2, max=9)
    @JsonIgnore
    private String id;

    @NotNull
    @Size(min=2, max=30)
    @ApiModelProperty(value = "Tenant Name", required = true)
    private String name;

    private String locale;

    @ApiModelProperty(value = "Tenant Description")
    private String description;


}
