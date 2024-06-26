package ru.rrk.manager.controller.diseases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rrk.manager.controller.diseases.payload.NewDiseasePayload;
import ru.rrk.manager.entity.Disease;
import ru.rrk.manager.restClients.BadRequestException;
import ru.rrk.manager.restClients.disease.DiseaseRestClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("clinic/diagnoses/diseases")
public class DiseasesController {
    private final DiseaseRestClient restClient;

    @GetMapping("list")
    public String getDiseasesList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("diseases", this.restClient.findAllDiseases(filter));
        model.addAttribute("filter", filter);
        return "clinic/diagnoses/diseases/list";
    }

    @GetMapping("create")
    public String getNewDiseasePage() {
        return "clinic/diagnoses/diseases/new_disease";
    }

    @PostMapping("create")
    public String createDisease(NewDiseasePayload payload, Model model) {
        try {
            Disease disease = this.restClient.createDisease(payload.code(), payload.description());
            return "redirect:/clinic/diagnoses/diseases/%d".formatted(disease.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "clinic/diagnoses/diseases/new_disease";
        }
    }
}
