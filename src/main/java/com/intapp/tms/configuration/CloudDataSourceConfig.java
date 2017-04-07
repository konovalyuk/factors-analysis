package com.intapp.tms.configuration;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * The type Cloud data source config.
 */
@Configuration
@Profile("cloud")
public class CloudDataSourceConfig extends AbstractCloudConfig {

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
}
