package ua.com.osmachko.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Valerii_Osmachko on 3/31/2017.
 */
@Configuration
@ComponentScan({"osmachko.com.ua"})
public class SpringRootConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
