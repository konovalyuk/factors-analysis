package com.intapp.tms.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The type Address dto.
 */
@Data
@NoArgsConstructor
public class AddressDTO implements Serializable {

    @JsonProperty("street")
    @ApiModelProperty(value = "Address Street")
    private String street;

    @NotNull(message = "city equals null")
    @NotBlank
    @JsonProperty("city")
    @ApiModelProperty(value = "Address City")
    private String city;

    @JsonProperty("state")
    @ApiModelProperty(value = "Address State")
    private String state;

    @NotNull(message = "country equals null")
    @NotBlank
    @JsonProperty("country")
    @ApiModelProperty(value = "Address Country")
    private String country;

    private String postalCode;

}
