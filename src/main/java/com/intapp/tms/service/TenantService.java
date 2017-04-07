package com.intapp.tms.service;

import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.service.dto.TenantDTOPost;
import com.intapp.tms.service.dto.TenantDTOGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class TenantService {

    private final TenantPersistenceService tenantPersistenceService;
    private final DtoConverter dtoConverter;
    private final Validator validator;

    @Value("${app.current.user}")
    private String currentUser;

    /**
     * Instantiates a new Tenant service.
     *
     * @param tenantPersistenceService the tenant persistence service
     * @param dtoConverter             the dto converter
     * @param validator                the validator
     */
    @Autowired
    public TenantService(TenantPersistenceService tenantPersistenceService, DtoConverter dtoConverter, Validator validator) {
        this.tenantPersistenceService = tenantPersistenceService;
        this.dtoConverter = dtoConverter;
        this.validator = validator;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<TenantDTOGet> findAll() {
        List<Tenant> tenantList = tenantPersistenceService.findAll();
        return dtoConverter.dtoListConvertor(tenantList, TenantDTOGet.class);
    }

    /**
     * Find by id tenant dto.
     *
     * @param tenantId the tenant id
     * @return the tenant dto
     */
    public TenantDTOGet findById(String tenantId) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        return dtoConverter.dtoConvertor(tenant, TenantDTOGet.class);
    }

    /**
     * Find by name tenant dto.
     *
     * @param tenantName the tenant name
     * @return the tenant dto
     */
    public TenantDTOGet findByName(String tenantName) {
        Tenant tenant = tenantPersistenceService.findByName(tenantName);
        return dtoConverter.dtoConvertor(tenant, TenantDTOGet.class);
    }

    /**
     * Create tenant dto.
     *
     * @param tenantDTOPost the tenant dto
     * @return the tenant dto
     */
    public TenantDTOPost create(TenantDTOPost tenantDTOPost) {
        Tenant tenant = dtoConverter.dtoConvertor(tenantDTOPost, Tenant.class);
        tenant = tenantPersistenceService.create(tenant);
        return dtoConverter.dtoConvertor(tenant, TenantDTOPost.class);
    }

    /**
     * Update tenant dto.
     *
     * @param tenantDTOPost the tenant dto
     * @return the tenant dto
     */
    public TenantDTOPost update(TenantDTOPost tenantDTOPost) {
        Tenant tenant = dtoConverter.dtoConvertor(tenantDTOPost, Tenant.class);
        tenant.setUpdatedBy(currentUser);
        tenant.setUpdatedAt(new Date());
        tenant = tenantPersistenceService.update(tenant);
        return dtoConverter.dtoConvertor(tenant, TenantDTOPost.class);
    }

    /**
     * Update tenant name tenant dto.
     *
     * @param tenantId   the tenant id
     * @param tenantName the tenant name
     * @return the tenant dto
     */
    public TenantDTOPost updateTenantName(String tenantId, String tenantName) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.updateTenantName(tenantId, tenantName);
        return dtoConverter.dtoConvertor(tenant, TenantDTOPost.class);
    }

    /**
     * Delete.
     *
     * @param tenantId the tenant id
     */
    public void delete(String tenantId) {
        validator.validateTenant(tenantId);

        tenantPersistenceService.delete(tenantId);
    }

    /**
     * Delete all.
     */
    public void deleteAll() {
        tenantPersistenceService.deleteAll();
    }

}
