package ru.rrk.manager.controller.vets.specialities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rrk.manager.controller.vets.specialities.payload.NewSpecialityPayload;
import ru.rrk.manager.entity.vet.Speciality;
import ru.rrk.manager.restClients.BadRequestException;
import ru.rrk.manager.restClients.vet.speciality.SpecialityRestClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("clinic/vets/specialities")
public class SpecialitiesController {
    private final SpecialityRestClient restClient;

    @GetMapping("list")
    public String getSpecialitiesList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("specialities", this.restClient.findAllSpecialities(filter));
        model.addAttribute("filter", filter);
        return "clinic/vets/specialities/list";
    }

    @GetMapping("create")
    public String getNewSpecialityPage() {
        return "clinic/vets/specialities/new_speciality";
    }

    @PostMapping("create")
    public String createSpeciality(NewSpecialityPayload payload, Model model) {
        try {
            Speciality speciality = this.restClient.createSpeciality(payload.name());
            return "redirect:/clinic/vets/specialities/%d".formatted(speciality.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "clinic/vets/specialities/new_speciality";
        }
    }
}