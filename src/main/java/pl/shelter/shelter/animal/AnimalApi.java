package pl.shelter.shelter.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.shelter.shelter.meal.MealService;
import pl.shelter.shelter.medicalCard.MedicalCard;
import pl.shelter.shelter.medicalCard.MedicalCardService;
import pl.shelter.shelter.owner.OwnerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animal")
@CrossOrigin
public class AnimalApi {

    private AnimalService animalService;
    @Autowired
    private MealService mealService;
    @Autowired
    private MedicalCardService medicalCardService;
    @Autowired
    private OwnerService ownerService;

    @Autowired
    public AnimalApi(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    public Iterable<Animal> getAll() {
        return animalService.findAllAnimals();
    }

    @GetMapping("/all/active")
    public Iterable<Animal> getActiveAnimals() {
        return animalService.findActiveAnimals();
    }

    @GetMapping("/last")
    public Iterable<Animal> getLastAnimals() {
        return animalService.findLastAnimals();
    }

    @GetMapping("/active")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfActiveAnimals() {
        return animalService.findNumberOfActiveAnimals();
    }

    @GetMapping("/adopted")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfAdoptedAnimals() {
        return animalService.findNumberOfAdoptedAnimals();
    }

    @GetMapping("/type")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfTypeAnimals() {
        return animalService.findNumberOfTypeAnimals();
    }

    @GetMapping("/cats")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfCats() {
        return animalService.findNumberOfCats();
    }

    @GetMapping("/males")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfMales() {
        return animalService.findNumberOfMales();
    }

    @GetMapping("/females")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfFemales() {
        return animalService.findNumberOfFemales();
    }

    @GetMapping("/older")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfOlderAnimals() {
        return animalService.findNumberOfOlderAnimals();
    }

    @GetMapping("/dogs")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfDogs() {
        return animalService.findNumberOfDogs();
    }

    @GetMapping("/other")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Integer findNumberOfOtherType() {
        return animalService.findNumberOfOtherType();
    }

    @GetMapping
    public Animal getById(@RequestParam Integer id) {
        return animalService.findAnimalById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public void deleteAnimal(@PathVariable Integer id) {
        try {
            mealService.deleteMealByAnimalId(id);
            medicalCardService.deleteMedicalCardByAnimalId(id);
            ownerService.deleteOwnerByAnimalId(id);
            animalService.deleteAnimalById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
