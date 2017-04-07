package com.intapp.tms.persistence.repository;

import com.intapp.tms.persistence.domain.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Tenant repository.
 */
public interface TenantRepository extends MongoRepository<Tenant, String> {

    /**
     * Find by id tenant.
     *
     * @param id the id
     * @return the tenant
     */
    Tenant findById(String id);

    /**
     * Find by name tenant.
     *
     * @param name the name
     * @return the tenant
     */
    Tenant findByName(String name);
}

