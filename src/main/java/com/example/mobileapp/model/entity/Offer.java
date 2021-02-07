package com.example.mobileapp.model.entity;

import com.example.mobileapp.model.enums.Engine;
import com.example.mobileapp.model.enums.Transmission;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends BaseEntity {

    @Length(min = 0, max = 512)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Vechicle engine type is required.")
    @Enumerated(value = EnumType.STRING)
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Vechicle mileage in km is required.")
    @Positive
    private Integer mileage;

    @NotNull(message = "Vechicle price is required.")
    @Positive
    private BigDecimal price;

    @NotNull(message = "Vechicle transmission type is required.")
    @Enumerated(value = EnumType.STRING)
    private Transmission transmission;

    @NotNull(message = "Vechicle manufacturing year is required.")
//    @PastOrPresent
    @Min(1900)
    private Integer year = LocalDateTime.now().getYear();


    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

    @NotNull(message = "Vechicle model is required.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ToString.Exclude
    private Model model;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User seller;


}
