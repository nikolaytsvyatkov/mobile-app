package com.example.mobileapp.web;

import com.example.mobileapp.model.Brand;
import com.example.mobileapp.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @ModelAttribute("brands")
    public Collection<Brand> addAttribute() {
        return this.brandService.getBrands();
    }

    @GetMapping("/all")
    public String getAllBrands() {
        return "brands";
    }
}
