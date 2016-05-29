package io.ona.company.waterpoints.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Configuration
@ComponentScan("io.ona.company.waterpoints")
public class AppConfig {

    public AppConfig() {

    }

    /**
     * @Bean public ObjectMapper createObjectMapper() {
     * 
     *       return new ObjectMapper(); }
     **/

    /**
     * @Bean public MappingJacksonHttpMessageConverter createJacksonConfig() {
     * 
     *       MappingJacksonHttpMessageConverter
     *       mappingJacksonHttpMessageConverter = new
     *       MappingJacksonHttpMessageConverter(); return
     *       mappingJacksonHttpMessageConverter; }
     **/

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

    /**
     * @Bean public ObjectMapper createObjectMapper() {
     * 
     *       return new ObjectMapper(); }
     * 
     * 
     * 
     * @Bean MappingJackson2HttpMessageConverter
     *       createMappingJackson2HttpMessageConverter( ObjectMapper
     *       objectMapper) {
     * 
     *       MappingJackson2HttpMessageConverter
     *       mappingJackson2HttpMessageConverter = new
     *       MappingJackson2HttpMessageConverter();
     * 
     *       //Jackson2ObjectMapperFactoryBean objectMapperFactory = new
     *       Jackson2ObjectMapperFactoryBean(); //ObjectMapper objectMapper =
     *       objectMapperFactory.
     * 
     *       mappingJackson2HttpMessageConverter.setObjectMapper( objectMapper
     *       );
     * 
     *       return mappingJackson2HttpMessageConverter; }
     **/

}
