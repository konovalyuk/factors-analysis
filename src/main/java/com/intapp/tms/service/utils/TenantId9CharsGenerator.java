package com.intapp.tms.service.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by maksymk on 4/4/2017.
 */
@Component("tenantId9CharsGenerator")
public class TenantId9CharsGenerator {

    private static final int LEN = 9;

    /**
     * Generate id string.
     *
     * @return the string
     */
    public String generateId(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, LEN);
    }

}
