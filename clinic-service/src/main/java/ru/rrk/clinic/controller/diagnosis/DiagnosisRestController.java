package ru.rrk.clinic.controller.diagnosis;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rrk.clinic.controller.diagnosis.payload.UpdateDiagnosisPayload;
import ru.rrk.clinic.entity.Diagnosis;
import ru.rrk.clinic.service.diagnosis.DiagnosisService;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("clinic-api/diagnoses/{diagnosisId:\\d+}")
public class DiagnosisRestController {
    private final DiagnosisService service;
    private final MessageSource messageSource;

    @ModelAttribute("diagnosis")
    public Diagnosis getDiagnosis(@PathVariable("diagnosisId") int diagnosisId) {
        return this.service.findDiagnosis(diagnosisId)
                .orElseThrow(() -> new NoSuchElementException("clinic.error.diagnosis.not_found"));
    }

    @GetMapping
    public Diagnosis findDiagnosis(@ModelAttribute("diagnosis") Diagnosis diagnosis) {
        return diagnosis;
    }

    @PatchMapping
    public ResponseEntity<?> updateDiagnosis(@PathVariable("diagnosisId") int diagnosisId,
                                             @Valid @RequestBody UpdateDiagnosisPayload payload,
                                             BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) throw exception;
            else throw new BindException(bindingResult);
        } else {
            this.service.updateDiagnosis(diagnosisId, payload.diseaseId(), payload.description());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable("diagnosisId") int diagnosisId) {
        this.service.deleteDiagnosis(diagnosisId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        Objects.requireNonNull(this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale))));
    }

}
