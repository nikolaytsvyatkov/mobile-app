package com.example.mobileapp.service;


import com.example.mobileapp.model.Brand;
import com.example.mobileapp.model.Model;

import java.util.Collection;
import java.util.List;

public interface BrandService {
    Collection<Brand> getBrands();
    Brand getBrandById(Long id);
    Brand createBrand(Brand post);
    Brand updateBrand(Brand post);
    Brand deleteBrand(Long id);
    long getBrandsCount();
    List<Brand> createBrandsBatch(List<Brand> posts);
    Model getModelById(Long id);
}
