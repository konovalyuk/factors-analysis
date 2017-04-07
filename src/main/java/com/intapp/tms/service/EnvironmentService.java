package com.intapp.tms.service;

import com.intapp.tms.exception.EnvironmentDoesExistException;
import com.intapp.tms.exception.EnvironmentNotFoundException;
import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.EnumStatus;
import com.intapp.tms.persistence.domain.Environment;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.service.dto.EnvironmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class EnvironmentService {

    private final TenantPersistenceService tenantPersistenceService;
    private final DtoConverter dtoConverter;
    private final Validator validator;

    @Value("${app.current.user}")
    private String currentUser;

    /**
     * Instantiates a new Environment service.
     *
     * @param tenantPersistenceService the tenant persistence service
     * @param dtoConverter             the dto converter
     * @param validator                the validator
     */
    @Autowired
    public EnvironmentService(TenantPersistenceService tenantPersistenceService, DtoConverter dtoConverter, Validator validator) {
        this.tenantPersistenceService = tenantPersistenceService;
        this.dtoConverter = dtoConverter;
        this.validator = validator;
    }

    /**
     * Find all list.
     *
     * @param tenantId the tenant id
     * @return the list
     */
    public List<EnvironmentDTO> findAll(String tenantId) {
        validator.validateTenant(tenantId);

        Map<String, Environment> environmentMap = tenantPersistenceService.findById(tenantId).getEnvironments();
        if (environmentMap != null) {
            return dtoConverter.dtoListConvertor(new ArrayList<>(environmentMap.values()),EnvironmentDTO.class);
        }
        return null;
    }

    /**
     * Find by name environment dto.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the environment dto
     */
    public EnvironmentDTO findByName(String tenantId, String environmentName) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Environment environment = tenant.getEnvironments().get(environmentName);

        if (environment == null) {
            throw new EnvironmentNotFoundException();
        }

        return dtoConverter.dtoConvertor(environment,EnvironmentDTO.class);
    }

    /**
     * Create environment environment dto.
     *
     * @param tenantId       the tenant id
     * @param environmentDTO the environment dto
     * @return the environment dto
     */
    public EnvironmentDTO createEnvironment(String tenantId, EnvironmentDTO environmentDTO) {
        validator.validateTenant(tenantId);
        Environment environment = dtoConverter.dtoConvertor(environmentDTO,Environment.class);
        environment.setStatus(EnumStatus.Inactive);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Environment> environmentMap = tenant.getEnvironments();

        if (environmentMap.containsKey(environment.getName())) {
            throw new EnvironmentDoesExistException();
        }

        environmentMap.put(environment.getName(), environment);
        tenant.setEnvironments(environmentMap);
        environment = tenantPersistenceService.update(tenant).getEnvironments().get(environment.getName());

        return dtoConverter.dtoConvertor(environment,EnvironmentDTO.class);
    }

    /**
     * Update environment environment dto.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param environmentDTO  the environment dto
     * @return the environment dto
     */
    public EnvironmentDTO updateEnvironment(String tenantId, String environmentName, EnvironmentDTO environmentDTO) {
        validator.validateTenant(tenantId);
        Environment environment = dtoConverter.dtoConvertor(environmentDTO,Environment.class);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Environment> environmentMap = tenant.getEnvironments();

        if (!environmentMap.containsKey(environmentName)) {
            throw new EnvironmentNotFoundException();
        }

        environmentMap.put(environmentName, environment);
        tenant.setEnvironments(environmentMap);
        environment = tenantPersistenceService.update(tenant).getEnvironments().get(environmentName);

        return dtoConverter.dtoConvertor(environment,EnvironmentDTO.class);
    }

    /**
     * Remove environment.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     */
    public void removeEnvironment(String tenantId, String environmentName) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Environment> environmentMap = tenant.getEnvironments();

        if (!environmentMap.containsKey(environmentName)) {
            throw new EnvironmentNotFoundException();
        }

        environmentMap.remove(environmentName);

        tenantPersistenceService.update(tenant);
    }

    /**
     * Find settings map.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @return the map
     */
    public Map<String, Object> findSettings(String tenantId, String environmentName) {
        validator.validateTenant(tenantId);

        return findByName(tenantId, environmentName).getSettings();
    }

    /**
     * Find setting object.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     * @return the object
     */
    public Object findSetting(String tenantId, String environmentName, String key) {
        validator.validateTenant(tenantId);

        return findSettings(tenantId, environmentName).get(key);
    }

    /**
     * Update settings map.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param newSettings     the new settings
     * @return the map
     */
    public Map<String, Object> updateSettings(String tenantId, String environmentName, Map<String, Object> newSettings) {
        validator.validateTenant(tenantId);

        EnvironmentDTO environmentDTO = findByName(tenantId, environmentName);
        Map<String, Object> settings = environmentDTO.getSettings();
        if (settings == null) {
            settings = new HashMap<>();
        }

        Map<String, Object> mergedSettings = new HashMap<>(settings);
        mergedSettings.putAll(newSettings);
        environmentDTO.setSettings(mergedSettings);

        return updateEnvironment(tenantId, environmentName, environmentDTO).getSettings();
    }

    /**
     * Update setting map.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     * @param value           the value
     * @return the map
     */
    public Map<String, Object> updateSetting(String tenantId, String environmentName, String key, Object value) {
        validator.validateTenant(tenantId);

        return this.updateSettings(tenantId, environmentName, new HashMap<String, Object>() {{
            put(key, value);
        }});
    }

    /**
     * Remove setting.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param key             the key
     */
    public void removeSetting(String tenantId, String environmentName, String key) {
        validator.validateTenant(tenantId);

        EnvironmentDTO environmentDTO = findByName(tenantId, environmentName);
        Map<String, Object> settings = environmentDTO.getSettings();

        settings.remove(key);

        updateEnvironment(tenantId, environmentName, environmentDTO);
    }

    /**
     * Activate.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     * @param validTo         the valid to
     */
    public void activate(String tenantId,String environmentName, Date validTo) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Environment> environmentMap = tenant.getEnvironments();
        Environment environment = environmentMap.get(environmentName);
        environment.setStatus(EnumStatus.Active);
        environment.setValidTo(validTo);
        environment.setValidFrom(new Date());

        environmentMap.put(environmentName, environment);
        tenant.setEnvironments(environmentMap);
        tenantPersistenceService.update(tenant);
    }

    /**
     * Deactivate.
     *
     * @param tenantId        the tenant id
     * @param environmentName the environment name
     */
    public void deactivate(String tenantId,String environmentName) {
        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Environment> environmentMap = tenant.getEnvironments();
        Environment environment = environmentMap.get(environmentName);
        environment.setStatus(EnumStatus.Inactive);

        environmentMap.put(environmentName, environment);
        tenant.setEnvironments(environmentMap);
        tenantPersistenceService.update(tenant);
    }
}
