package ru.rrk.users.receptionist.controller.checkup.payload;

import java.time.LocalDate;
import java.time.LocalTime;

public record NewCheckupSummaryPayload(
        LocalDate date,
        LocalTime time,
        Integer petId,
        Integer vetId,
        Integer typeId
) {
}
