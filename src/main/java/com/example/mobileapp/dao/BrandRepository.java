package com.example.mobileapp.dao;


import com.example.mobileapp.model.Brand;
import com.example.mobileapp.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("select m from Model m where m.id = :id")
    Optional<Model> findModelById(@Param("id") Long id);

}
