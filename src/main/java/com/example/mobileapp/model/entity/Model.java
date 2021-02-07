package com.example.mobileapp.model.entity;

import com.example.mobileapp.model.enums.Category;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model extends BaseEntity{

    @NotNull(message = "Please enter a model name!")
    @Length(min = 3, max = 15, message = "The model name must be between 3 and 15 characters long!")
    private String name;

    @NotNull(message = "Please enter a category!")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Length(min = 3, max = 15, message = "The image url must be between 8 and 512 characters long!")
    @NotNull
    @Column(name = "image_url")
    private String imageUrl;


    @Min(1900)
    @NotNull
    @Column(name = "start_year")
    private Integer startYear;

    @Min(1900)
    @Column(name = "end_year")
    private Integer endYear;

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

    @NotNull
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

}
