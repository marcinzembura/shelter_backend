package pl.shelter.shelter.meal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal,Integer> {

    List<Meal>findMealsByAnimal_Name(String name);

    List<Meal>findMealsByAnimal_Id(Integer id);




//    @Modifying
//    @Query("delete from Meal b where b.id_animal=:id")
//    void deleteMealByAnimalId(Integer id);

//    @Query(nativeQuery = true, value = "SELECT COUNT(a.id) FROM animal a WHERE a.age>10 and a.status = 1")
//    Integer getNumberOfOlderAnimals();

//    @Query(nativeQuery = true, value = "SELECT a.id FROM meal a WHERE a.id_animal=:idAnimal")
//    List<Integer> getMealIdByAnimalId(Integer idAnimal);

    @Query(nativeQuery = true, value = "SELECT a.id FROM meal a WHERE a.id_animal=:idAnimal")
   List<Integer> getMealIdByAnimalId(Integer idAnimal);
}
