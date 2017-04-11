package com.intapp.tms.service;

import com.google.common.collect.Maps;
import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.service.dto.TenantDTOPost;
import com.intapp.tms.service.dto.TenantDTOGet;
import com.intapp.tms.service.utils.TenantId9CharsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class TenantService {

    private final TenantPersistenceService tenantPersistenceService;
    private final TenantId9CharsGenerator tenantIdGenerator;

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
     * @param tenantIdGenerator        the tenant id generator
     */
    public TenantService(TenantPersistenceService tenantPersistenceService,
                         DtoConverter dtoConverter,
                         Validator validator,
                         TenantId9CharsGenerator tenantIdGenerator) {
        this.tenantPersistenceService = tenantPersistenceService;
        this.dtoConverter = dtoConverter;
        this.validator = validator;
        this.tenantIdGenerator = tenantIdGenerator;
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
        tenant.setId(tenantIdGenerator.generateId());
        tenant.setCreatedBy(currentUser);
        tenant.setCreatedAt(new Date());
        tenant.setProducts(Maps.newConcurrentMap());
        tenant.setEnvironments(Maps.newConcurrentMap());
        tenant.setLocale(Locale.getDefault());
        tenant.setDefaultLocale(Locale.US);
        tenant.setTimeZone(TimeZone.getDefault());
        tenant = tenantPersistenceService.save(tenant);
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
        validator.validateTenant(tenant.getId());

        tenant.setUpdatedBy(currentUser);
        tenant.setUpdatedAt(new Date());
        tenant = tenantPersistenceService.save(tenant);
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

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        tenant.setName(tenantName);
        tenant = tenantPersistenceService.save(tenant);
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
