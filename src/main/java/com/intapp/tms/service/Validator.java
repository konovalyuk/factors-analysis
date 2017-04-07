package com.intapp.tms.service;

import com.intapp.tms.exception.TenantNotFoundException;
import com.intapp.tms.persistence.TenantPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class Validator {

    private static Logger logger = LoggerFactory.getLogger(Validator.class);

    /**
     * The Tenant persistence service.
     */
    protected final TenantPersistenceService tenantPersistenceService;

    /**
     * Instantiates a new Validator.
     *
     * @param tenantPersistenceService the tenant persistence service
     */
    @Autowired
    protected Validator(TenantPersistenceService tenantPersistenceService) {
        this.tenantPersistenceService = tenantPersistenceService;
    }

    /**
     * Validate tenant.
     *
     * @param tenantId the tenant id
     */
    protected void validateTenant(String tenantId) {
        if (tenantPersistenceService.findById(tenantId) == null) {
            logger.info("Throw TenantNotFoundException - ask hander to return 'error' view");
            throw new TenantNotFoundException(tenantId);
        }
    }

}
