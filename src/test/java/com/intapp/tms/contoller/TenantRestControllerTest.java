package com.intapp.tms.contoller;

import com.intapp.tms.persistence.domain.Tenant;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by aaltukhov on 2017-03-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TenantRestControllerTest extends TestRestController {

    /**
     * Gets tenant by name test 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void getTenantByNameTest1() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEMO_TENANT_NAME);

        mockMvc.perform(get(TENNAT_REST_API_URL + "findBy/" + tenant.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(tenant.getId())))
                .andExpect(jsonPath("$.name", is(tenant.getName())));
        //.andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
    }

    /**
     * Gets tenant by id test 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void getTenantByIdTest2() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEV_TENANT_NAME);

        mockMvc.perform(get(TENNAT_REST_API_URL + tenant.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(tenant.getId())))
                .andExpect(jsonPath("$.name", is(tenant.getName())));
        //.andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
    }

    /**
     * Gets all tenants test 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllTenantsTest3() throws Exception {
        Tenant tenantCloudDev = tenantMap.get(CLOUD_DEV_TENANT_NAME);
        Tenant tenantCloudDemo = tenantMap.get(CLOUD_DEMO_TENANT_NAME);

        mockMvc.perform(get(TENNAT_REST_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(tenantCloudDev.getId())))
                .andExpect(jsonPath("$[0].name", is(tenantCloudDev.getName())))
                .andExpect(jsonPath("$[1].id", is(tenantCloudDemo.getId())))
                .andExpect(jsonPath("$[1].name", is(tenantCloudDemo.getName())));
    }

    /**
     * Create tenant test 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void createTenantTest4() throws Exception {
        String tenantJson = json(new Tenant("NewTenant"));

        mockMvc.perform(post(TENNAT_REST_API_URL)
                .contentType(contentType)
                .content(tenantJson))
                .andExpect(status().isCreated());
    }

    /**
     * Create tenant test 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void createTenantTest5() throws Exception {
        Tenant tenant = tenantMap.get(CLOUD_DEV_TENANT_NAME);

        mockMvc.perform(delete(TENNAT_REST_API_URL + tenant.getId()))
                .andExpect(status().isOk());
    }
}
