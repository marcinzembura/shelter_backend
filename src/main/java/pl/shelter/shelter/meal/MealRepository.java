package pl.shelter.shelter.meal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal,Integer> {

    List<Meal>findMealsByAnimal_Name(String name);
}
