package pl.shelter.shelter.owner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Integer> {


    @Query(nativeQuery = true, value = "SELECT a.id FROM owner a WHERE a.id_animal=:idAnimal")
    List<Integer> getIdOwnerByAnimalId(Integer idAnimal);


}
