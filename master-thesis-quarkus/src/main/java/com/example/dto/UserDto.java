package com.example.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record UserDto(
        Long id,
        String email,
        String username,
        String firstName,
        String surname
) {
}
