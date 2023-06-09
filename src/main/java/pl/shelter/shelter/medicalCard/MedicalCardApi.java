package pl.shelter.shelter.medicalCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.shelter.shelter.owner.Owner;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicalcard")
@CrossOrigin
public class MedicalCardApi {

    private MedicalCardService medicalCardService;

    @Autowired
    public MedicalCardApi(MedicalCardService medicalCardService) {
        this.medicalCardService = medicalCardService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Iterable<MedicalCard> getAll() {
        return medicalCardService.findAllMedicalCards();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public MedicalCard getById(@RequestParam Integer id) {
        return medicalCardService.findMedicalCardById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public MedicalCard addMedicalCard(@RequestBody MedicalCard medicalCard) {
        return medicalCardService.saveMedicalCard(medicalCard);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public MedicalCard updateAnimal(@RequestBody  MedicalCard medicalCard) {
        return medicalCardService.updateMedicalCard(medicalCard);
    }

    @DeleteMapping(value="/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public void deleteMedicalCardById(@PathVariable Integer id) {
        medicalCardService.deleteMedicalCardById(id);
    }
}


