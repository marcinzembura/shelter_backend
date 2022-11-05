package pl.shelter.shelter.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@CrossOrigin
public class AnimalApi {

    private AnimalService animalService;

    @Autowired
    public AnimalApi(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<Animal> getAll() {
        return animalService.findAllAnimals();
    }

    @GetMapping
    public List<Animal> getById(@RequestParam Integer id) {
        return animalService.findAnimalById(id);
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @PutMapping
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @DeleteMapping
    public void deleteAnimal(@RequestParam Integer id) {
        animalService.deleteAnimalById(id);
    }
}
