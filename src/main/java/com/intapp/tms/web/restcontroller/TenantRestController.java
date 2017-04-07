package com.intapp.tms.web.restcontroller;

import com.intapp.tms.service.TenantService;
import com.intapp.tms.service.dto.TenantDTOPost;
import com.intapp.tms.service.dto.TenantDTOGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The type Tenant rest controller.
 */
@RestController
@RequestMapping("api/${app.current.version}/tenants")
@ComponentScan(basePackages = "com.intapp.tms")
public class TenantRestController{

    private static Logger logger = LoggerFactory.getLogger(TenantRestController.class);

    /**
     * The Tenant service.
     */
    protected final TenantService tenantService;

    /**
     * Instantiates a new Tenant rest controller.
     *
     * @param tenantService the tenant service
     */
    @Autowired
    protected TenantRestController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    /**
     * Find all tenants response entity.
     *
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TenantDTOGet>> findAllTenants() {
        return ResponseEntity.ok(tenantService.findAll());
    }

    /**
     * Find tenant by id response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{tenantId}")
    public ResponseEntity<TenantDTOGet> findTenantById(@PathVariable String tenantId) {
        return ResponseEntity.ok(tenantService.findById(tenantId));
    }

    /**
     * Find tenants by names response entity.
     *
     * @param tenantName the tenant name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "findBy/{tenantName}")
    public ResponseEntity<TenantDTOGet> findTenantsByNames(@PathVariable String tenantName) {
        return ResponseEntity.ok(tenantService.findByName(tenantName));
    }

    /**
     * Create tenant response entity.
     *
     * @param tenant the tenant
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<TenantDTOPost> createTenant(@RequestBody TenantDTOPost tenant) {
        TenantDTOPost result = tenantService.create(tenant);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{tenantId}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update tenant name tenant dto.
     *
     * @param tenantId   the tenant id
     * @param tenantName the tenant name
     * @return the tenant dto
     */
    @RequestMapping(method = RequestMethod.POST, path = "/{tenantId}/updateName")
    public TenantDTOPost updateTenantName(
            @PathVariable String tenantId,
            @RequestBody String tenantName) {

        return tenantService.updateTenantName(tenantId, tenantName);
    }

    /**
     * Remove tenant response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{tenantId}")
    public ResponseEntity<?> removeTenant(@PathVariable String tenantId) {

        tenantService.delete(tenantId);

        return ResponseEntity.ok("TenantDTOPost has been removed.");
    }

}

