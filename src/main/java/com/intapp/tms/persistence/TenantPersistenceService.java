package com.intapp.tms.persistence;

import com.intapp.tms.persistence.domain.Tenant;

import java.util.Date;
import java.util.List;

/**
 * Created by aaltukhov on 2017-03-07.
 */
public interface TenantPersistenceService {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Tenant> findAll();

    /**
     * Find by id tenant.
     *
     * @param tenantId the tenant id
     * @return the tenant
     */
    Tenant findById(String tenantId);

    /**
     * Find by name tenant.
     *
     * @param tenantName the tenant name
     * @return the tenant
     */
    Tenant findByName(String tenantName);

    /**
     * Create tenant.
     *
     * @param tenant the tenant
     * @return the tenant
     */
    Tenant save(Tenant tenant);

    /**
     * Delete.
     *
     * @param tenantId the tenant id
     */
    void delete(String tenantId);

    /**
     * Delete all.
     */
    void deleteAll();

}

