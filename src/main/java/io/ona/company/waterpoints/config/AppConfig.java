package io.ona.company.waterpoints.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * Configures Spring beans programmatically.
 *
 * @author Ndung'u Mbiyu
 */
@Configuration
@ComponentScan("io.ona.company.waterpoints")
public class AppConfig {

    public AppConfig() {
    }

    @SuppressWarnings("deprecation")
    @Bean
    public AnnotationMethodHandlerAdapter createAnnotationMethodHandler() {

        AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter =
                new AnnotationMethodHandlerAdapter();

        HttpMessageConverter<?>[] httpMessageConverters =
                new HttpMessageConverter[1];

        httpMessageConverters[0] = new MappingJackson2HttpMessageConverter();

        annotationMethodHandlerAdapter
                .setMessageConverters(httpMessageConverters);
        return annotationMethodHandlerAdapter;
    }
}
