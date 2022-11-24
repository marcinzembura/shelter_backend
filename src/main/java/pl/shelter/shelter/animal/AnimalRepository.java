package pl.shelter.shelter.animal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {

    List<Animal> findAnimalByName(String name);

    Animal findAnimalById(Integer id);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM animal d " +
            "WHERE d.status = 1 " +
            "ORDER BY id DESC " +
            "LIMIT 5")
    ArrayList<Animal> getLastFiveActiveAnimals();

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM animal d " +
            "WHERE d.status = 1 ")
    ArrayList<Animal> getActiveAnimals();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.status=1")
    Integer getNumberOfActiveAnimals();


    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.status=0")
    Integer getNumberOfAdoptedAnimals();

    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT (a.type_of_animal)) FROM animal a")
    Integer getNumberOfTypeAnimals();

    @Modifying
    @Query(nativeQuery = true, value="update animal a set a.status=1")
    Boolean changeAnimalStatus();

}
