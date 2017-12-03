package au.agl.dj.pets.config;

import au.agl.dj.pets.common.http.AglClientRequestInterceptor;
import au.agl.dj.pets.common.http.AglResponseErrorHandler;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    @Bean
    RestTemplateBuilder restTemplateBuilder(final AglResponseErrorHandler aglResponseErrorHandler,
                                            final AglClientRequestInterceptor aglClientRequestInterceptor) {
        final RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.errorHandler(aglResponseErrorHandler);
        builder.additionalInterceptors(aglClientRequestInterceptor);
        builder.additionalMessageConverters(new MappingJackson2HttpMessageConverter());
        return builder;
    }

    @Bean
    RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); //can be configured from application.yml
        executor.setMaxPoolSize(2); //can be configured from application.yml
        executor.setQueueCapacity(500); //can be configured from application.yml
        executor.setThreadNamePrefix("PetsExecutor"); //can be configured from application.yml
        executor.initialize();
        return executor;
    }


    @Bean
    ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        return mapper;
    }
}
