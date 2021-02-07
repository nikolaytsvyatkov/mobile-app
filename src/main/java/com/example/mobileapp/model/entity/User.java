package com.example.mobileapp.model.entity;

import com.example.mobileapp.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  extends BaseEntity{


    @Length(min = 3, max = 50, message = "The username must be between 3 and 15 characters long!")
    @Column(unique = true, nullable = false)
    @NotNull
    @EqualsAndHashCode.Include
    private String username;

    @NotNull(message = "First name is required!")
    @Length(min = 3, max = 50, message = "The first name must be between 3 and 15 characters long!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is required!")
    @Length(min = 3, max = 50, message = "The last name must be between 3 and 15 characters long!")
    @Column(name = "last_name")
    private String lastName;


    @NotNull(message = "Password is required!")
    @Length(min = 4, max = 20, message = "The password must be between 3 and 15 characters long!")
    private String password;

    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Length(min = 3, max = 15, message = "The image url must be between 8 and 512 characters long!")
    @NotNull
    @Column(name = "image_url")
    private String imageUrl;


    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

}
