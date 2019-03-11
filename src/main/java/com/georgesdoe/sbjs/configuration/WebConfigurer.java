package com.georgesdoe.sbjs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableHandlerCustomizer(){
        return pageableResolver -> {
            pageableResolver.setMaxPageSize(50);
            pageableResolver.setSizeParameterName("page_size");
        };
    }

    @Bean
    public SortHandlerMethodArgumentResolverCustomizer sortArgumentCustomizer(){
        return sortResolver -> sortResolver.setSortParameter("order_by");
    }
}
