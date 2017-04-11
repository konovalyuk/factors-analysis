package com.intapp.tms.contoller;

import com.google.common.collect.Maps;
import com.intapp.tms.persistence.domain.Tenant;
import com.intapp.tms.persistence.TenantPersistenceService;
import com.intapp.tms.service.EnvironmentService;
import com.intapp.tms.service.ProductService;
import com.intapp.tms.service.dto.EnvironmentDTO;
import com.intapp.tms.service.dto.ProductDTO;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by aaltukhov on 2017-03-12.
 */
public abstract class TestRestController {

    /**
     * The constant contentType.
     */
    protected static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /**
     * The constant REST_API_VERSION.
     */
    protected static final String REST_API_VERSION = "v1";
    /**
     * The constant TENNAT_REST_API_URL.
     */
    protected static final String TENNAT_REST_API_URL = "/api/" + REST_API_VERSION + "/tenants/";
    /**
     * The constant PRODUCT_REST_API_URL_FORMAT.
     */
    protected static final String PRODUCT_REST_API_URL_FORMAT = TENNAT_REST_API_URL + "%s/products/";
    /**
     * The constant ENV_REST_API_URL_FORMAT.
     */
    protected static final String ENV_REST_API_URL_FORMAT = TENNAT_REST_API_URL + "%s/environments/";

    /**
     * The constant CLOUD_DEV_TENANT_NAME.
     */
    protected static final String CLOUD_DEV_TENANT_NAME = "CloudDev";
    /**
     * The constant CLOUD_DEMO_TENANT_NAME.
     */
    protected static final String CLOUD_DEMO_TENANT_NAME = "CloudDemo";
    /**
     * The constant TENANT_NAMES_LIST.
     */
    protected static final List<String> TENANT_NAMES_LIST = Arrays.asList(CLOUD_DEV_TENANT_NAME, CLOUD_DEMO_TENANT_NAME);

    /**
     * The constant OPEN_CLOUD_PRODUCT_NAME.
     */
    protected static final String OPEN_CLOUD_PRODUCT_NAME = "OpenCloud";
    /**
     * The constant TIME_CLOUD_PRODUCT_NAME.
     */
    protected static final String TIME_CLOUD_PRODUCT_NAME = "TimeCloud";
    /**
     * The constant EXPERIENCE_PRODUCT_NAME.
     */
    protected static final String EXPERIENCE_PRODUCT_NAME = "Experience";
    /**
     * The constant PRODUCT_NAMES_LIST.
     */
    protected static final List<String> PRODUCT_NAMES_LIST = Arrays.asList(OPEN_CLOUD_PRODUCT_NAME, TIME_CLOUD_PRODUCT_NAME, EXPERIENCE_PRODUCT_NAME);

    /**
     * The constant PRODUCT_SETTINGS_MAP.
     */
    protected static final Map<String, Object> PRODUCT_SETTINGS_MAP = new HashMap<String, Object>() {{
        put("StringKey1", "Value");
        put("DateTimeKey1", new Date());
        put("IntKey1", 123);
    }};

    /**
     * The constant DEV_ENV_NAME.
     */
    protected static final String DEV_ENV_NAME = "DEV";
    /**
     * The constant QA_ENV_NAME.
     */
    protected static final String QA_ENV_NAME = "QA";
    /**
     * The constant PROD_ENV_NAME.
     */
    protected static final String PROD_ENV_NAME = "PROD";
    /**
     * The constant ENV_NAMES_LIST.
     */
    protected static final List<String> ENV_NAMES_LIST = Arrays.asList(DEV_ENV_NAME, QA_ENV_NAME, PROD_ENV_NAME);

    /**
     * The constant ENV_SETTINGS_MAP.
     */
    protected static final Map<String, Object> ENV_SETTINGS_MAP = new HashMap<String, Object>() {{
        put("StringKey1", "Value");
        put("DateTimeKey1", new Date());
        put("IntKey1", 123);
    }};

    /**
     * The Tenant map.
     */
    protected Map<String, Tenant> tenantMap;
    /**
     * The Mock mvc.
     */
    protected MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    /**
     * The Web application context.
     */
    @Autowired
    protected WebApplicationContext webApplicationContext;
    /**
     * The Tenant service.
     */
    @Autowired
    protected TenantPersistenceService tenantService;
    /**
     * The Product service.
     */
    @Autowired
    protected ProductService productService;
    /**
     * The Environment service.
     */
    @Autowired
    protected EnvironmentService environmentService;

    /**
     * Sets .
     *
     * @throws Exception the exception
     */
    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        tenantService.deleteAll();

        tenantMap = Maps.newConcurrentMap();
        for (String tenantName : TENANT_NAMES_LIST) {
            Tenant tenant = tenantService.save(new Tenant(tenantName));

            for (String productName : PRODUCT_NAMES_LIST) {
                ProductDTO productDTO = new ProductDTO(productName);
                productDTO.setSettings(PRODUCT_SETTINGS_MAP);
                productService.createProduct(tenant.getId(), productDTO);
            }

            for (String envName : ENV_NAMES_LIST) {
                EnvironmentDTO environmentDTO = new EnvironmentDTO(envName);
                environmentDTO.setSettings(ENV_SETTINGS_MAP);
                environmentService.createEnvironment(tenant.getId(), environmentDTO);
            }
        }

        for (Tenant tenant : tenantService.findAll()) {
            tenantMap.put(tenant.getName(), tenant);
        }
    }

    @Autowired
    private void setConverters(HttpMessageConverter<?>[] converters) {
        mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", mappingJackson2HttpMessageConverter);
    }

    /**
     * Json string.
     *
     * @param o the o
     * @return the string
     * @throws IOException the io exception
     */
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    /**
     * Compose rest api url string.
     *
     * @param rootUrlFormat the root url format
     * @param tenantId      the tenant id
     * @return the string
     */
    protected String composeRestApiUrl(String rootUrlFormat, String tenantId) {
        String url = String.format(rootUrlFormat, tenantId);
        System.out.println(url);
        return url;
    }

    /**
     * Compose rest api url string.
     *
     * @param rootUrlFormat the root url format
     * @param tenantId      the tenant id
     * @param name          the name
     * @return the string
     */
    protected String composeRestApiUrl(String rootUrlFormat, String tenantId, String name) {
        String url = composeRestApiUrl(rootUrlFormat, tenantId) + name + '/';
        System.out.println(url);
        return url;
    }

    /**
     * Compose rest api url string.
     *
     * @param rootUrlFormat the root url format
     * @param tenantId      the tenant id
     * @param productName   the product name
     * @param settings      the settings
     * @return the string
     */
    protected String composeRestApiUrl(String rootUrlFormat, String tenantId, String productName, Map<String, Object> settings) {
        String url = composeRestApiUrl(rootUrlFormat, tenantId, productName) + "settings";
        System.out.println(url);
        return url;
    }
}
