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
            "LIMIT 6")
    ArrayList<Animal> getLastSixActiveAnimals();

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM animal d " +
            "WHERE d.status = 1 ")
    ArrayList<Animal> getActiveAnimals();


    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.status=1")
    Integer getNumberOfActiveAnimals();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.type_of_animal='Kot' and a.status = 1")
    Integer getNumberOfCats();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.type_of_animal='Pies' and a.status = 1")
    Integer getNumberOfDogs();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.gender='Samiec' and a.status = 1")
    Integer getNumberOfMales();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.gender='Samica' and a.status = 1")
    Integer getNumberOfFemales();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.age>10 and a.status = 1")
    Integer getNumberOfOlderAnimals();

    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.type_of_animal='Inny' and a.status = 1")
    Integer getNumberOfOther();
    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.status=0")
    Integer getNumberOfAdoptedAnimals();

    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT (a.type_of_animal)) FROM animal a")
    Integer getNumberOfTypeAnimals();

    @Modifying
    @Query(nativeQuery = true, value="update animal a set a.status=1")
    Boolean changeAnimalStatus();

}
