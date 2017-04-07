package com.intapp.tms.contoller;

import com.intapp.tms.persistence.domain.Product;
import com.intapp.tms.persistence.domain.Tenant;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by aaltukhov on 2017-03-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRestControllerTest extends TestRestController {

    private final static String NEW_PRODUCT_NAME = "NewProduct";

    /**
     * Gets product by name test 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void getProductByNameTest1() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEMO_TENANT_NAME);
        Product product = tenant.getProducts().get(OPEN_CLOUD_PRODUCT_NAME);

        mockMvc.perform(get(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId(), product.getName())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.settings", allOf(hasEntry("StringKey1", "Value"))));
    }

    /**
     * Create product test 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void createProductTest2() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEMO_TENANT_NAME);
        Product product = new Product(NEW_PRODUCT_NAME);

        mockMvc.perform(post(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId()))
                .contentType(contentType)
                .content(json(product)))
                .andExpect(status().isCreated());
    }

    /**
     * Create product and update settings test 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void createProductAndUpdateSettingsTest3() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEMO_TENANT_NAME);
        Product product = new Product(NEW_PRODUCT_NAME);
        Map<String, Object> newSettings = new HashMap<String, Object>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};

        Map<String, Object> updateSettings = new HashMap<String, Object>() {{
            put("3", 3);
            put("4", 4);
            put("5", 5);
        }};

        mockMvc.perform(post(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId()))
                .contentType(contentType)
                .content(json(product)))
                .andExpect(status().isCreated());

        mockMvc.perform(put(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId(), product.getName(), newSettings))
                .contentType(contentType)
                .content(json(newSettings)))
                .andExpect(status().isOk());

        mockMvc.perform(put(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId(), product.getName(), updateSettings))
                .contentType(contentType)
                .content(json(updateSettings)))
                .andExpect(status().isOk());

        mockMvc.perform(get(composeRestApiUrl(PRODUCT_REST_API_URL_FORMAT, tenant.getId(), product.getName())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.settings", allOf(hasEntry("1", 1), hasEntry("2", 2), hasEntry("3", 3), hasEntry("4", 4), hasEntry("5", 5))));
    }
}
