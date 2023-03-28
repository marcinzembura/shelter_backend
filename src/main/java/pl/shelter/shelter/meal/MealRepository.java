package pl.shelter.shelter.meal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal,Integer> {

    List<Meal>findMealsByAnimal_Name(String name);

    List<Meal>findMealsByAnimal_Id(Integer id);

    @Query(nativeQuery = true, value = "SELECT a.id FROM meal a WHERE a.id_animal=:idAnimal")
   List<Integer> getMealIdByAnimalId(Integer idAnimal);
}
