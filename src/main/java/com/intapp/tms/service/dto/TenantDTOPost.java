package com.intapp.tms.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
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

    @JsonIgnore
    @Size(max=9)
    private String id;

    @NotNull(message = "not null message")
    @Size(min=2, max=30)
    @JsonProperty("name")
    @NotBlank
    @ApiModelProperty(value = "Tenant Name", required = true)
    private String name;

    private String locale;

    @JsonProperty("description")
    @ApiModelProperty(value = "Tenant Description")
    private String description;


}
