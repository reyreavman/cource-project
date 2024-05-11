package ru.rrk.clinic.controller.label.payload;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record NewLabelPayload(
        @NotNull(message = "{clinic.labels.create.errors.value_is_null}")
        String value,
        @Nullable
        Date date
) {
}
