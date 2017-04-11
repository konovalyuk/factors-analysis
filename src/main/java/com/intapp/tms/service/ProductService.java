package com.intapp.tms.service;

import com.intapp.tms.service.exception.ProductDoesExistException;
import com.intapp.tms.service.exception.ProductNotFoundException;
import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.persistence.domain.Product;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.service.converter.DtoConverter;
import com.intapp.tms.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class ProductService {

    private final TenantPersistenceService tenantPersistenceService;
    private final DtoConverter dtoConverter;
    private final Validator validator;

    @Value("${app.current.user}")
    private String currentUser;

    /**
     * Instantiates a new Product service.
     *
     * @param tenantPersistenceService the tenant persistence service
     * @param dtoConverter             the dto converter
     * @param validator                the validator
     */
    @Autowired
    public ProductService(TenantPersistenceService tenantPersistenceService, DtoConverter dtoConverter, Validator validator) {
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
    public List<ProductDTO> findAll(String tenantId) {
        validator.validateTenant(tenantId);

        Map<String, Product> productMap = tenantPersistenceService.findById(tenantId).getProducts();
        if (productMap != null) {
            return dtoConverter.dtoListConvertor(new ArrayList<>(productMap.values()),ProductDTO.class);
        }
        return null;
    }

    /**
     * Find by name product dto.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the product dto
     */
    public ProductDTO findByName(String tenantId, String productName) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Product product = tenant.getProducts().get(productName);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return dtoConverter.dtoConvertor(product,ProductDTO.class);
    }

    /**
     * Create product product dto.
     *
     * @param tenantId   the tenant id
     * @param productDTO the product dto
     * @return the product dto
     */
    public ProductDTO createProduct(String tenantId, ProductDTO productDTO) {
        validator.validateTenant(tenantId);
        Product product = dtoConverter.dtoConvertor(productDTO,Product.class);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Product> productsMap = tenant.getProducts();

        if (productsMap.containsKey(product.getName())) {
            throw new ProductDoesExistException();
        }

        productsMap.put(product.getName(), product);
        tenant.setProducts(productsMap);
        product = tenantPersistenceService.save(tenant).getProducts().get(product.getName());

        return dtoConverter.dtoConvertor(product,ProductDTO.class);
    }

    /**
     * Update product product dto.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param productDTO  the product dto
     * @return the product dto
     */
    public ProductDTO updateProduct(String tenantId, String productName, ProductDTO productDTO) {
        validator.validateTenant(tenantId);
        Product product = dtoConverter.dtoConvertor(productDTO,Product.class);

        Tenant tenant = this.tenantPersistenceService.findById(tenantId);
        Map<String, Product> productsMap = tenant.getProducts();

        if (!productsMap.containsKey(productName)) {
            throw new ProductNotFoundException();
        }

        productsMap.put(productName, product);
        tenant.setProducts(productsMap);
        product = tenantPersistenceService.save(tenant).getProducts().get(productName);

        return dtoConverter.dtoConvertor(product,ProductDTO.class);
    }

    /**
     * Remove product.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     */
    public void removeProduct(String tenantId, String productName) {
        validator.validateTenant(tenantId);

        Tenant tenant = tenantPersistenceService.findById(tenantId);
        Map<String, Product> products = tenant.getProducts();

        if (!products.containsKey(productName)) {
            throw new ProductNotFoundException();
        }

        products.remove(productName);

        tenantPersistenceService.save(tenant);
    }

    /**
     * Find settings map.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the map
     */
    public Map<String, Object> findSettings(String tenantId, String productName) {
        validator.validateTenant(tenantId);

        return findByName(tenantId, productName).getSettings();
    }

    /**
     * Find setting object.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     * @return the object
     */
    public Object findSetting(String tenantId, String productName, String key) {
        validator.validateTenant(tenantId);

        return findSettings(tenantId, productName).get(key);
    }

    /**
     * Update setting map.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     * @param value       the value
     * @return the map
     */
    public Map<String, Object> updateSetting(String tenantId, String productName, String key, Object value) {
        validator.validateTenant(tenantId);

        return updateSettings(tenantId, productName, new HashMap<String, Object>() {{
            put(key, value);
        }});
    }

    /**
     * Update settings map.
     *
     * @param tenantId       the tenant id
     * @param productName    the product name
     * @param updateSettings the update settings
     * @return the map
     */
    public Map<String, Object> updateSettings(String tenantId, String productName, Map<String, Object> updateSettings) {
        validator.validateTenant(tenantId);

        ProductDTO productDTO = findByName(tenantId, productName);
        Map<String, Object> settings = productDTO.getSettings();
        if (settings == null) {
            settings = new HashMap<>();
        }

        Map<String, Object> mergedSettings = new HashMap<>(settings);
        mergedSettings.putAll(updateSettings);
        productDTO.setSettings(mergedSettings);

        return updateProduct(tenantId, productName, productDTO).getSettings();
    }

    /**
     * Remove setting.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     */
    public void removeSetting(String tenantId, String productName, String key) {
        validator.validateTenant(tenantId);

        ProductDTO productDTO = findByName(tenantId, productName);
        Map<String, Object> settings = productDTO.getSettings();

        settings.remove(key);

        updateProduct(tenantId, productName, productDTO);
    }
}
