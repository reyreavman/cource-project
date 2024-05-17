package ru.rrk.clinic.controller.appointment.result.payload;

import jakarta.validation.constraints.NotNull;

public record NewAppointmentResultPayload(
        @NotNull
        Integer currentAppointmentId,
        Integer nextAppointmentId,
        @NotNull
        Integer stateId,
        Integer diagnosisId,
        String advice,
        String prescription
) {
}
