package com.georgesdoe.abe.configuration;

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
        return new PageableHandlerMethodArgumentResolverCustomizer() {
            @Override
            public void customize(PageableHandlerMethodArgumentResolver pageableResolver) {
                pageableResolver.setMaxPageSize(50);
                pageableResolver.setSizeParameterName("page_size");
            }
        };
    }

    @Bean
    public SortHandlerMethodArgumentResolverCustomizer sortArgumentCustomizer(){
        return new SortHandlerMethodArgumentResolverCustomizer() {
            @Override
            public void customize(SortHandlerMethodArgumentResolver sortResolver) {
                sortResolver.setSortParameter("order_by");
            }
        };
    }
}
