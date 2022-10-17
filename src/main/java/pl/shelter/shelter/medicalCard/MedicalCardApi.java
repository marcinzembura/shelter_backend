package pl.shelter.shelter.medicalCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shelter.shelter.animal.Animal;
import pl.shelter.shelter.animal.AnimalService;

import java.util.List;

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
    public Iterable<MedicalCard> getAll() {
        return medicalCardService.findAllMedicalCards();
    }

    @GetMapping("/{id}")
    public List<MedicalCard> getById(@RequestParam Integer id) {
        return medicalCardService.findMedicalCardById(id);
    }

    @PostMapping
    public MedicalCard addMedicalCard(@RequestBody MedicalCard medicalCard) {
        return medicalCardService.saveMedicalCard(medicalCard);
    }

    @PutMapping
    public MedicalCard updateAnimal(@RequestBody  MedicalCard medicalCard) {
        return medicalCardService.saveMedicalCard(medicalCard);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalCardById(@RequestParam Integer id) {
        medicalCardService.deleteMedicalCardById(id);
    }
}


