package com.Retrozone.retrozone_bd.services;

import com.Retrozone.retrozone_bd.dto.UsersDTO;
import com.Retrozone.retrozone_bd.model.Users;
import com.Retrozone.retrozone_bd.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // Validamos el FORMATO de la contraseña aquí, sobre el valor que escribió
    // el usuario, antes de encriptarla. Si esto viviera como @Pattern en la
    // entidad Users, Hibernate lo evaluaría sobre el hash de BCrypt ya
    // guardado en el campo (justo antes del INSERT), que nunca cumple el
    // patrón — por eso NO puede ir ahí.
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    private void validatePasswordFormat(String rawPassword) {
        if (rawPassword == null || !PASSWORD_PATTERN.matcher(rawPassword).matches()) {
            throw new IllegalArgumentException(
                    "El password debe tener 8 caracteres como minimo, una letra y un numero"
            );
        }
    }

    private UsersDTO createResponse(Users user){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        usersDTO.setFullName(user.getFullName());
        usersDTO.setUserName(user.getUserName());
        usersDTO.setEmail(user.getEmail());
        usersDTO.setPhone(user.getPhone());
        usersDTO.setRegistrationDate(user.getRegistrationDate());
        usersDTO.setAddress(user.getAddress());
        return usersDTO;
    }

    public List<UsersDTO> getAllUsers(){
        return usersRepository.findAll().stream().map(user -> createResponse(user)).toList();
    }

    public UsersDTO getUserById(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Este jugador todavía no se ha unido a la partida")
        );
        return createResponse(user);
    }

    public UsersDTO createUser(Users user){
        validatePasswordFormat(user.getPassword());
        String encriptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encriptedPassword);
        usersRepository.save(user);
        return createResponse(user);
    }

    public UsersDTO updateUserById(Long id, Users userUpdtates){
        Users ogUser = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Este jugador todavía no se ha unido a la partida")
        );
        if (userUpdtates.getFullName() != null) ogUser.setFullName(userUpdtates.getFullName());
        if (userUpdtates.getUserName() != null) ogUser.setUserName(userUpdtates.getUserName());
        if (userUpdtates.getEmail() != null) ogUser.setEmail(userUpdtates.getEmail());
        if (userUpdtates.getPassword() != null) {
            validatePasswordFormat(userUpdtates.getPassword());
            ogUser.setPassword(passwordEncoder.encode(userUpdtates.getPassword()));
        }
        if (userUpdtates.getPhone() != null) ogUser.setPhone(userUpdtates.getPhone());
        if (userUpdtates.getRegistrationDate() != null) ogUser.setRegistrationDate(userUpdtates.getRegistrationDate());
        if (userUpdtates.getAddress() != null) ogUser.setAddress(userUpdtates.getAddress());
        usersRepository.save(ogUser);
        return createResponse(ogUser);
    }

    public UsersDTO deleteUserById(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Este jugador todavía no se ha unido a la partida")
        );
        usersRepository.delete(user);
        return createResponse(user);
    }

    public Boolean loginUser(Users user){
        Users savedUser = usersRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Jugador no encontrado")
        );
        return passwordEncoder.matches(user.getPassword(), savedUser.getPassword());
    }

}
