package com.Retrozone.retrozone_bd.services;

import com.Retrozone.retrozone_bd.dto.UsersDTO;
import com.Retrozone.retrozone_bd.model.Users;
import com.Retrozone.retrozone_bd.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

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
        if (userUpdtates.getPassword() != null) ogUser.setPassword(userUpdtates.getPassword());
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
