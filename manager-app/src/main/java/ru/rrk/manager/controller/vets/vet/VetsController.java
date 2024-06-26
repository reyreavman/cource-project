package ru.rrk.manager.controller.vets.vet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rrk.manager.controller.vets.vet.payload.NewVetPayload;
import ru.rrk.manager.entity.vet.Vet;
import ru.rrk.manager.restClients.BadRequestException;
import ru.rrk.manager.restClients.vet.speciality.SpecialityRestClient;
import ru.rrk.manager.restClients.vet.vet.VetRestClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("clinic/vets")
public class VetsController {
    private final VetRestClient vetRestClient;
    private final SpecialityRestClient specialityRestClient;

    @GetMapping("list")
    public String getVetsList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("vets", this.vetRestClient.findAllVets(filter));
        model.addAttribute("filter", filter);
        return "clinic/vets/list";
    }

    @GetMapping("create")
    public String getNewVetPage(Model model) {
        model.addAttribute("specialities", this.specialityRestClient.findAllSpecialities(null));
        return "clinic/vets/new_vet";
    }

    @PostMapping("create")
    public String createVet(NewVetPayload payload, Model model) {
        try {
            Vet vet = this.vetRestClient.createVet(payload.firstName(), payload.lastName(), payload.specialityId());
            return "redirect:/clinic/vets/%d".formatted(vet.getId());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "clinic/vets/new_vet";
        }
    }
}
