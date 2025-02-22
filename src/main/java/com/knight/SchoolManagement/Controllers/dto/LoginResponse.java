package com.knight.SchoolManagement.Controllers.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
