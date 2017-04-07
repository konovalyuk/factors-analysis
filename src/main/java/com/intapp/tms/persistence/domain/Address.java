package com.intapp.tms.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * The type Address.
 */
@Data
@NoArgsConstructor
public class Address implements Serializable {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

}
