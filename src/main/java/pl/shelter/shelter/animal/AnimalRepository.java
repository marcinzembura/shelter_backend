package pl.shelter.shelter.animal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {

    List<Animal>findAnimalByName(String name);
    List<Animal>findAnimalById(Integer id);
}
