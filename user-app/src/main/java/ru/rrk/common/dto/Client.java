package ru.rrk.common.dto;

public record Client(
        int id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email) {
}
