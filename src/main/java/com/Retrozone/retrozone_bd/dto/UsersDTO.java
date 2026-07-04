package com.Retrozone.retrozone_bd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  UsersDTO {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String registrationDate;
    private String address;

}
