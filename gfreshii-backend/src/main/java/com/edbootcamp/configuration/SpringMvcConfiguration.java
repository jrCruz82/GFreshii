package com.edbootcamp.configuration;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
@ComponentScan("com.edbootcamp")
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	    MappingJackson2HttpMessageConverter messageConverter = new  MappingJackson2HttpMessageConverter();

	    ObjectMapper mapper = new ObjectMapper();
	    //Registering Hibernate4Module to support lazy objects
	    mapper.registerModule(new Hibernate4Module());

	    messageConverter.setObjectMapper(mapper);
	    return messageConverter;

	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    //Here we add our custom-configured HttpMessageConverter
	    converters.add(jacksonMessageConverter());
	    super.configureMessageConverters(converters);
	}

}
