package com.bsu.project;

import com.bsu.project.config.*;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author Gulshirin Berdiyeva
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected String[] getServletMappings() {
                return new String[] {"/"};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
                return new Class[] {
                        ThymeleafConfiguration.class,
                        DataSourceConfiguration.class,
                        EntityManagerFactoryConfiguration.class,
                        TransactionManagerConfiguration.class,
                        PasswordEncryptionConfiguration.class,
                        MultipartResolverConfiguration.class
                };
        }

        @Override
        protected Class<?>[] getRootConfigClasses() {
                return null;
        }

        @Override
        protected Filter[] getServletFilters() {
                CharacterEncodingFilter filter = new CharacterEncodingFilter();

                filter.setEncoding("UTF-8");
                filter.setForceEncoding(true);

                return new Filter[] { filter };
        }
}