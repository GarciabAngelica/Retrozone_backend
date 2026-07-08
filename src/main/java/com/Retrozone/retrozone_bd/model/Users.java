ppackage com.Retrozone.retrozone_bd.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_fullname")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "user_password_hash")
    private String password;

    @Column(name = "user_registration_date")
    private String registrationDate;

    @Column(name = "user_address")
    private String address;

    // RELACIÓN CON ADDRESS
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
}