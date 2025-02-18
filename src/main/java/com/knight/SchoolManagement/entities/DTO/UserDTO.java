package com.knight.SchoolManagement.entities.DTO;

import com.knight.SchoolManagement.entities.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class UserDTO {

    private String name;
    private UUID id;
    private LocalDate registration;
    private User.Role role;

    public UserDTO(){}

    public UserDTO(User user) {
        this.name = user.getName();
        this.id = user.getId();
        this.registration = user.getRegistration();
        this.role = user.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
