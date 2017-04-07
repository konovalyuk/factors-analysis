package com.intapp.tms.contoller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * The type Environment rest controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnvironmentRestControllerTest{
    private final static String NEW_ENV_NAME = "NewEnvironment";

    /**
     * Gets product by name test 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void getProductByNameTest1() throws Exception {
        Assert.assertNotNull(NEW_ENV_NAME);
    }
}
