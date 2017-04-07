package com.intapp.tms.web.restcontroller;

import com.intapp.tms.service.ProductService;
import com.intapp.tms.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The type Product rest controller.
 */
@RestController
@RequestMapping("api/{version}/tenants/{tenantId}/products")
public class ProductRestController {

    private final ProductService productService;

    /**
     * Instantiates a new Product rest controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Find all products response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<ProductDTO>> findAllProducts(@PathVariable(required = true) String tenantId) {
        return ResponseEntity.ok(productService.findAll(tenantId));
    }

    /**
     * Find product response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{productName}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable String tenantId, @PathVariable String productName) {
        return ResponseEntity.ok(productService.findByName(tenantId, productName));
    }

    /**
     * Create product response entity.
     *
     * @param tenantId the tenant id
     * @param product  the product
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> createProduct(
            @PathVariable String tenantId,
            @RequestBody ProductDTO product) {

        ProductDTO result = productService.createProduct(tenantId, product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productName}")
                .buildAndExpand(result.getName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update product response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param product     the product
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{productName}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String tenantId,
            @PathVariable String productName,
            @RequestBody ProductDTO product) {

        productService.updateProduct(tenantId, productName, product);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove product response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{productName}")
    public ResponseEntity<?> removeProduct(@PathVariable String tenantId, @PathVariable String productName) {
        productService.removeProduct(tenantId, productName);

        return ResponseEntity.ok().build();
    }

    /**
     * Find all settings response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{productName}/settings")
    public ResponseEntity<Map<String, Object>> findAllSettings(@PathVariable String tenantId, @PathVariable String productName) {
        return ResponseEntity.ok(productService.findSettings(tenantId, productName));
    }

    /**
     * Find setting response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{productName}/settings/{key}")
    public ResponseEntity<?> findSetting(@PathVariable String tenantId, @PathVariable String productName, @PathVariable String key) {
        return ResponseEntity.ok(productService.findSetting(tenantId, productName, key));
    }

    /**
     * Update settings response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param settings    the settings
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{productName}/settings")
    public ResponseEntity<?> updateSettings(@PathVariable String tenantId, @PathVariable String productName, @RequestBody HashMap<String, Object> settings) {

        productService.updateSettings(tenantId, productName, settings);

        return ResponseEntity.ok().build();
    }

    /**
     * Update setting response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     * @param value       the value
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{productName}/settings/{key}")
    public ResponseEntity<?> updateSetting(@PathVariable String tenantId, @PathVariable String productName, @PathVariable String key, @RequestBody Object value) {
        productService.updateSetting(tenantId, productName, key, value);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove setting response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @param key         the key
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{productName}/settings/{key}")
    public ResponseEntity<?> removeSetting(@PathVariable String tenantId, @PathVariable String productName, @PathVariable String key) {
        productService.removeSetting(tenantId, productName, key);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove all settings response entity.
     *
     * @param tenantId    the tenant id
     * @param productName the product name
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{productName}/settings")
    public ResponseEntity<?> removeAllSettings(@PathVariable String tenantId, @PathVariable String productName) {
        productService.updateSettings(tenantId, productName, new HashMap<>());

        return ResponseEntity.ok().build();
    }
}
