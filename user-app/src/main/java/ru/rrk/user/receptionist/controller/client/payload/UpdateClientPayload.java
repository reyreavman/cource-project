package ru.rrk.user.receptionist.controller.client.payload;

public record UpdateClientPayload(
        String firstName,
        String lastName,
        String phoneNumber,
        String email) {
}
