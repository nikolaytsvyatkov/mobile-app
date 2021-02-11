package com.example.mobileapp.util;

import com.example.mobileapp.model.Model;
import com.example.mobileapp.service.BrandService;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;

public class StringIdToEntityConverter implements Converter<String, Model> {

    private BrandService brandService;

    public StringIdToEntityConverter(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public Model convert(String modelId) {
        if (!modelId.trim().equals("")) {
            return this.brandService.getModelById(Long.parseLong(modelId));
        }
        return null;
    }
}

