package com.intapp.tms.persistence.springdataimpl;

import com.google.common.collect.Maps;
import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.persistence.repository.TenantRepository;
import com.intapp.tms.service.utils.TenantId9CharsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * The type Tenant persistence service.
 */
@Repository
public class TenantPersistenceServiceImpl implements TenantPersistenceService {

    @Value("${app.current.user}")
    private String currentUser;

    @Autowired
    private TenantRepository repository;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    @Qualifier("tenantId9CharsGenerator")
    private TenantId9CharsGenerator tenantIdGenerator;

    @Override
    public List<Tenant> findAll() {
        return repository.findAll();
    }

    @Override
    public Tenant findById(String tenantId) {
        return repository.findById(tenantId);
    }

    @Override
    public Tenant findByName(String tenantName) {
        return repository.findByName(tenantName);
    }

    @Override
    public Tenant create(Tenant tenant) {
        tenant.setId(tenantIdGenerator.generateId());
        tenant.setCreatedBy(currentUser);
        tenant.setCreatedAt(new Date());
        tenant.setProducts(Maps.newConcurrentMap());
        tenant.setEnvironments(Maps.newConcurrentMap());
        tenant.setLocale(Locale.getDefault());
        tenant.setDefaultLocale(Locale.US);
        tenant.setTimeZone(TimeZone.getDefault());
        return repository.save(tenant);
    }

    @Override
    public Tenant update(Tenant tenant) {
        tenant.setUpdatedBy(currentUser);
        tenant.setUpdatedAt(new Date());
        return repository.save(tenant);
    }

    @Override
    public Tenant updateTenantName(String tenantId, String tenantName) {
        Tenant tenant = findById(tenantId);
        tenant.setName(tenantName);
        return update(tenant);
    }

    @Override
    public void delete(String tenantId) {
        Tenant tenant = findById(tenantId);
        repository.delete(tenant.getId());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
