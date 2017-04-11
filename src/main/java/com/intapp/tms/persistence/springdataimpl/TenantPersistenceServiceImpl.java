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
    public Tenant save(Tenant tenant) {
        return repository.save(tenant);
    }

    @Override
    public void delete(String tenantId) {
        repository.delete(tenantId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
