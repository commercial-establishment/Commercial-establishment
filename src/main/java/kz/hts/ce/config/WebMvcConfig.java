package kz.hts.ce.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.hts.ce.util.converters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "kz.hts.ce")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private StringToGender stringToGenderConverter;
    @Autowired
    private StringToCity stringToCityConverter;
    @Autowired
    private StringToType stringToTypeConverter;
    @Autowired
    private StringToCategory stringToCategoryConverter;
    @Autowired
    private StringToArea stringToAreaConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToGenderConverter);
        registry.addConverter(stringToCityConverter);
        registry.addConverter(stringToTypeConverter);
        registry.addConverter(stringToCategoryConverter);
        registry.addConverter(stringToAreaConverter);

    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru_RU"));
        resolver.setCookieName("establishmentLocaleCookie");
        resolver.setCookieMaxAge(4800);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("establishmentLocale");
        registry.addInterceptor(interceptor);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        MappingJackson2HttpMessageConverter jacksonConverter = new
                MappingJackson2HttpMessageConverter();
        jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/json")));
        jacksonConverter.setObjectMapper(jacksonObjectMapper());
        return jacksonConverter;
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
