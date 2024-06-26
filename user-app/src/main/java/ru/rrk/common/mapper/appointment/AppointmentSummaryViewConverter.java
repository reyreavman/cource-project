package ru.rrk.common.mapper.appointment;

import org.springframework.core.convert.converter.Converter;
import ru.rrk.common.dto.appointment.Appointment;
import ru.rrk.common.viewModels.appointment.AppointmentSummaryView;

public class AppointmentSummaryViewConverter implements Converter<Appointment, AppointmentSummaryView> {
    @Override
    public AppointmentSummaryView convert(Appointment appointment) {
        return AppointmentSummaryView.builder()
                .id(appointment.id())
                .petId(appointment.pet().id())
                .petName(appointment.pet().name())
                .vetId(appointment.vet().getId())
                .vetFullName(appointment.vet().getFirstName().concat(" ").concat(appointment.vet().getLastName()))
                .time(appointment.time())
                .description(appointment.description())
                .checkup(appointment.checkup() == null ? null : appointment.checkup())
                .build();
    }
}
