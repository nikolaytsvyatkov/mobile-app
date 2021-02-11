package com.example.mobileapp.config;

import com.example.mobileapp.service.BrandService;
import com.example.mobileapp.util.StringIdToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private BrandService brandService;

    @Autowired
    public WebConfig(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringIdToEntityConverter(brandService));
    }
}
