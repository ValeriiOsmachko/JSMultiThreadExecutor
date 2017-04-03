package ua.com.osmachko.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Valerii_Osmachko on 3/17/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"osmachko.com.ua"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

}
