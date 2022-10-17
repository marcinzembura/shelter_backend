package pl.shelter.shelter.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shelter.shelter.animal.Animal;
import pl.shelter.shelter.animal.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
@CrossOrigin
public class MealApi {

    private MealService mealService;

    @Autowired
    public MealApi(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/all")
    public Iterable<Meal> getAllMeals() {
        return mealService.findAllMeals();
    }

    @GetMapping
    public List<Meal> getMealByAnimalId(@RequestParam Integer id) {
        return mealService.findMealByAnimalId(id);
    }

    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @PutMapping
    public Meal updateAnimal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @DeleteMapping
    public void deleteMeal(@RequestParam Integer id) {
        mealService.deleteMealById(id);
    }
}
