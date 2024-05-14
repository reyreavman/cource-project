package ru.rrk.clinic.service.appointment.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rrk.clinic.entity.appointment.AppointmentResultState;
import ru.rrk.clinic.repository.appointment.result.AppointmentResultStateRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentResultServiceImpl implements AppointmentResultService {
    private final AppointmentResultStateRepository repository;

    @Override
    public Iterable<AppointmentResultState> findAllStates() {
        return this.repository.findAll();
    }

    @Override
    public AppointmentResultState createState(String state) {
        return this.repository.save(new AppointmentResultState(null, state));
    }

    @Override
    public Optional<AppointmentResultState> findState(int stateId) {
        return this.repository.findById(stateId);
    }

    @Override
    public void updateState(int stateId, String state) {
        this.repository.findById(stateId)
                .ifPresentOrElse(curState -> curState.setState(state),
                        () -> {
                            throw new NoSuchElementException();
                        });
    }

    @Override
    public void deleteState(int stateId) {
        this.repository.deleteById(stateId);
    }
}
