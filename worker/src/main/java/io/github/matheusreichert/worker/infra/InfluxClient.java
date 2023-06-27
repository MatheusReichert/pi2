package io.github.matheusreichert.worker.infra;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
public class InfluxClient implements EnvironmentAware {
    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }



    @Bean
    public static InfluxDBClient getClient() {
        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = environment.getRequiredProperty("userBucket.token");
        String dbUrl = environment.getRequiredProperty("userBucket.dbUrl");

        return InfluxDBClientFactory.create(dbUrl, token.toCharArray());
    }


}
