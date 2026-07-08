package com.Retrozone.retrozone_bd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "address_street_and_number")
    private String streetAndNumber;

    @Column(name = "address_state")
    private String state;

    @Column(name = "address_country")
    private String country;

    @Column(name = "code")
    private String code;

    @Column(name = "address_postal")
    private Integer postalCode;

    // RELACIÓN CON USERS
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}