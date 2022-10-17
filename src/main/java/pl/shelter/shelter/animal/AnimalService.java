package pl.shelter.shelter.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(@Autowired AnimalRepository repository) {
        this.animalRepository = repository;
    }

    public List<Animal> findAnimalById(Integer id) {
        return animalRepository.findAnimalById(id);
    }

    public Iterable<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimalById(Integer id) {
        animalRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
/*        animalRepository.save(new Animal("Puma", "Dog", 8, 10, false, "1", "1"));
        animalRepository.save(new Animal("Sonar", "Dog", 7, 10, false, "1", "1"));
        animalRepository.save(new Animal("Paco", "Dog", 4, 15, true, "1", "1"));
        animalRepository.save(new Animal("Hektor", "Dog", 17, 30, true, "1", "1"));
        animalRepository.save(new Animal("Misiek", "Dog", 13, 16, true, "1", "1"));*/
    }


}
