package com.intapp.tms.service.dto;

import com.intapp.tms.persistence.domain.Address;
import com.intapp.tms.persistence.domain.Environment;
import com.intapp.tms.persistence.domain.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by maksymk on 4/7/2017.
 */
@Data
@NoArgsConstructor
@Document(collection = "tenants")
public class TenantDTOGet implements Serializable{

    @NotNull
    @Size(min=2, max=9)
    @ApiModelProperty(value = "Tenant id", required = true)
    private String id;

    @NotNull
    @Size(min=2, max=30)
    @ApiModelProperty(value = "Tenant Name", required = true)
    private String name;

    private Address address;

    private TimeZone timeZone;
    private String defaultLocale;
    private String locale;

    private Map<String, Environment> environments;
    private Map<String, Product> products;

    @ApiModelProperty(value = "Tenant Description")
    private String description;

    @CreatedDate
    private Date createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Date updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
