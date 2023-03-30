package pl.shelter.shelter.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.exception.ApiException;
import pl.shelter.shelter.exception.ApiRequestException;

import java.util.NoSuchElementException;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(@Autowired AnimalRepository repository) {
        this.animalRepository = repository;
    }

    public Animal findAnimalById(Integer id) {
        return animalRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Cannot find animal for this ID"));
    }

    public Iterable<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    public Iterable<Animal> findActiveAnimals() {
        return animalRepository.getActiveAnimals();
    }

    public Iterable<Animal> findLastAnimals() {
        return animalRepository.getLastSixActiveAnimals();
    }

    public Integer findNumberOfActiveAnimals() {
        return animalRepository.getNumberOfActiveAnimals();
    }

    public Integer findNumberOfAdoptedAnimals() {
        return animalRepository.getNumberOfAdoptedAnimals();
    }

    public Integer findNumberOfTypeAnimals() {
        return animalRepository.getNumberOfTypeAnimals();
    }

    public Integer findNumberOfCats() {
        return animalRepository.getNumberOfCats();
    }

    public Integer findNumberOfDogs() {
        return animalRepository.getNumberOfDogs();
    }

    public Integer findNumberOfOtherType() {return animalRepository.getNumberOfOther();}

    public Integer findNumberOfMales() {
        return animalRepository.getNumberOfMales();
    }

    public Integer findNumberOfFemales() {
        return animalRepository.getNumberOfFemales();
    }

    public Integer findNumberOfOlderAnimals() {
        return animalRepository.getNumberOfOlderAnimals();
    }

    public Animal saveAnimal(Animal animal) {
        try {
            return animalRepository.save(animal);
        } catch (DataAccessException e) {
            throw new ApiRequestException("Cannot save animal!");
        }
    }

    public void deleteAnimalById(Integer id) throws Exception {
        if (animalRepository.existsById(id)) {
            try {
                animalRepository.deleteById(id);
            } catch (DataAccessException | NoSuchElementException e) {
                throw new ApiRequestException("Cannot delete animal for this ID");
            }
        }
    }


}
