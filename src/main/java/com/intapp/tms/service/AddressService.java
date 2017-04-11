package com.intapp.tms.service;

import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.Address;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.service.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Address service.
 */
@Service
public class AddressService {

    private final TenantPersistenceService tenantPersistenceService;
    private final DtoConverter dtoConverter;
    private final Validator validator;

    /**
     * Instantiates a new Address service.
     *
     * @param tenantPersistenceService the tenant persistence service
     * @param dtoConverter             the dto converter
     * @param validator                the validator
     */
    @Autowired
    public AddressService(TenantPersistenceService tenantPersistenceService, DtoConverter dtoConverter, Validator validator) {
        this.tenantPersistenceService = tenantPersistenceService;
        this.dtoConverter = dtoConverter;
        this.validator = validator;
    }

    /**
     * Find address address dto.
     *
     * @param tenantId the tenant id
     * @return the address dto
     */
    public AddressDTO findAddress(String tenantId) {
        validator.validateTenant(tenantId);

        Address address = tenantPersistenceService.findById(tenantId).getAddress();
        if (address != null) {
            AddressDTO addressDTO = dtoConverter.dtoConvertor(address, AddressDTO.class);
            return addressDTO;
        }
        return null;
    }

    /**
     * Update address address dto.
     *
     * @param tenantId   the tenant id
     * @param addressDTO the address dto
     * @return the address dto
     */
    public AddressDTO updateAddress(String tenantId, AddressDTO addressDTO) {
        validator.validateTenant(tenantId);

        Address address = dtoConverter.dtoConvertor(addressDTO, Address.class);
        Tenant tenant = tenantPersistenceService.findById(tenantId);
        tenant.setAddress(address);
        address = tenantPersistenceService.save(tenant).getAddress();

        return dtoConverter.dtoConvertor(address, AddressDTO.class);
    }
}
