package com.intapp.tms.web.restcontroller;

import com.intapp.tms.service.EnvironmentService;
import com.intapp.tms.service.dto.EnvironmentDTO;
import com.intapp.tms.service.validator.EnvironmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The type Environment rest controller.
 */
@RestController
@RequestMapping("api/{version}/tenants/{tenantId}/environments")
public class EnvironmentRestController {

    private final EnvironmentService environmentService;

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new EnvironmentValidator());
    }

    /**
     * Instantiates a new Environment rest controller.
     *
     * @param environmentService the environment service
     */
    protected EnvironmentRestController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    /**
     * Find all environments response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<?> findAllEnvironments(@PathVariable String tenantId) {
        return ResponseEntity.ok(environmentService.findAll(tenantId));
    }

    /**
     * Find environment response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{environmentName}")
    public ResponseEntity<?> findEnvironment(@PathVariable String tenantId, @PathVariable String environmentName) {
        return ResponseEntity.ok(environmentService.findByName(tenantId, environmentName));
    }

    /**
     * Create environment response entity.
     *
     * @param tenantId    the tenant id
     * @param environment the environment
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createEnvironment(
            @PathVariable String tenantId,
            @Valid @RequestBody EnvironmentDTO environment) {

        EnvironmentDTO result = environmentService.createEnvironment(tenantId, environment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{environmentName}")
                .buildAndExpand(result.getName())
                .toUri();

        return ResponseEntity.ok(location);
    }

    /**
     * Update environment response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param environment     the environment
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{environmentName}")
    public ResponseEntity<?> updateEnvironment(
            @PathVariable String tenantId,
            @PathVariable String environmentName,
            @Valid @RequestBody EnvironmentDTO environment) {

        environmentService.updateEnvironment(tenantId, environmentName, environment);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove environment response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{environmentName}")
    public ResponseEntity<?> removeEnvironment(@PathVariable String tenantId, @PathVariable String environmentName) {
        environmentService.removeEnvironment(tenantId, environmentName);

        return ResponseEntity.ok().build();
    }

    /**
     * Find all settings response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{environmentName}/settings")
    public ResponseEntity<?> findAllSettings(@PathVariable String tenantId, @PathVariable String environmentName) {
        return ResponseEntity.ok(environmentService.findSettings(tenantId, environmentName));
    }

    /**
     * Find setting response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{environmentName}/settings/{key}")
    public ResponseEntity<?> findSetting(@PathVariable String tenantId, @PathVariable String environmentName, @PathVariable String key) {
        return ResponseEntity.ok(environmentService.findSetting(tenantId, environmentName, key));
    }

//    TODO: add validator for settings

    /**
     * Update settings response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param settings        the settings
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{environmentName}/settings")
    public ResponseEntity<?> updateSettings(@PathVariable String tenantId, @PathVariable String environmentName, @RequestBody HashMap<String, Object> settings) {
        environmentService.updateSettings(tenantId, environmentName, settings);

        return ResponseEntity.ok().build();
    }
// TODO: add validator for value

    /**
     * Update setting response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     * @param value           the value
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{environmentName}/settings/{key}")
    public ResponseEntity<?> updateSetting(@PathVariable String tenantId, @PathVariable String environmentName, @PathVariable String key, @RequestBody Object value) {
        environmentService.updateSetting(tenantId, environmentName, key, value);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove setting response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{environmentName}/settings/{key}")
    public ResponseEntity<?> removeSetting(@PathVariable String tenantId, @PathVariable String environmentName, @PathVariable String key) {
        environmentService.removeSetting(tenantId, environmentName, key);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove all settings response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{environmentName}/settings")
    public ResponseEntity<?> removeAllSettings(@PathVariable String tenantId, @PathVariable String environmentName) {
        environmentService.updateSettings(tenantId, environmentName, new HashMap<>());

        return ResponseEntity.ok().build();
    }

    /**
     * Activate environment response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param validTo         the valid to
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = "/{tenantId}/activate/{environmentName}")
    public ResponseEntity<?> activateEnvironment(@PathVariable String tenantId,
                                            @PathVariable String environmentName,
                                            @RequestBody Date validTo) {
        environmentService.activate(tenantId,environmentName, validTo);

        return ResponseEntity.ok("TenantDTOPost has been activated.");
    }

    /**
     * Deactivate environment response entity.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = "/{tenantId}/deactivate/{environmentName}")
    public ResponseEntity<?> deactivateEnvironment(@PathVariable String tenantId,
                                                   @PathVariable String environmentName) {
        environmentService.deactivate(tenantId,environmentName);
        return ResponseEntity.ok("TenantDTOPost has been deactivated.");
    }
}
