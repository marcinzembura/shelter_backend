package pl.shelter.shelter.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(@Autowired AnimalRepository repository) {
        this.animalRepository = repository;
    }

    public Animal findAnimalById(Integer id) {
        return animalRepository.findAnimalById(id);
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
    public Integer findNumberOfActiveAnimals(){return animalRepository.getNumberOfActiveAnimals();}
    public Integer findNumberOfAdoptedAnimals(){return animalRepository.getNumberOfAdoptedAnimals();}
    public Integer findNumberOfTypeAnimals(){return animalRepository.getNumberOfTypeAnimals();}
    public Integer findNumberOfCats(){return animalRepository.getNumberOfCats();}
    public Integer findNumberOfDogs(){return animalRepository.getNumberOfDogs();}
    public Integer findNumberOfOtherType(){return animalRepository.getNumberOfOther();}
    public Integer findNumberOfMales(){return animalRepository.getNumberOfMales();}

    public Integer findNumberOfFemales(){return animalRepository.getNumberOfFemales();}

    public Integer findNumberOfOlderAnimals(){return animalRepository.getNumberOfOlderAnimals();}

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimalById(Integer id) throws Exception {
        if(animalRepository.existsById(id)) {
            try{
                animalRepository.deleteById(id);
            }catch (Exception e){
                throw new Exception ("Cannot delete animal by id", e);
            }
        }
    }




}
