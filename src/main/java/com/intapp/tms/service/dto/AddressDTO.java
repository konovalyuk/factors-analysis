package com.intapp.tms.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Address dto.
 */
@Data
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

}
