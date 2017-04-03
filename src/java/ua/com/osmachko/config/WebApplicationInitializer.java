package ua.com.osmachko.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



/**
 * Created by Valerii_Osmachko on 3/16/2017.
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ApplicationConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
