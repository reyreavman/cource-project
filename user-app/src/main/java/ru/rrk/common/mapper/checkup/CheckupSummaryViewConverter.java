package ru.rrk.common.mapper.checkup;

import org.springframework.core.convert.converter.Converter;
import ru.rrk.common.viewModels.checkup.CheckupSummaryView;
import ru.rrk.common.dto.checkup.Checkup;

public class CheckupSummaryViewConverter implements Converter<Checkup, CheckupSummaryView> {
    @Override
    public CheckupSummaryView convert(Checkup checkup) {
        return CheckupSummaryView.builder()
                .id(checkup.id())
                .time(checkup.time())
                .petId(checkup.pet().id())
                .petName(checkup.pet().name())
                .vetId(checkup.vet().getId())
                .vetName(checkup.vet().getFirstName().concat(" ").concat(checkup.vet().getLastName()))
                .state(checkup.checkupState().state())
                .build();
    }
}
