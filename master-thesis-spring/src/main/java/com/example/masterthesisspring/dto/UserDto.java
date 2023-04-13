package com.example.masterthesisspring.dto;

public record UserDto(
        Long id,
        String email,
        String username,
        String firstName,
        String surname
) {
}
