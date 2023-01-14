package pl.shelter.shelter.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
@CrossOrigin("*")
public class MealApi {

    private MealService mealService;

    @Autowired
    public MealApi(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Iterable<Meal> getAllMeals() {
        return mealService.findAllMeals();
    }

//    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
//    public List<Meal> getMealByAnimalId(@RequestParam Integer id) {
//        return mealService.findMealByAnimalId(id);
//    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Meal addMeal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Meal updateAnimal(@RequestBody Meal meal) {
        return mealService.updateMeal(meal);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @DeleteMapping(value="/{id}")
    public void deleteMeal(@PathVariable Integer id) {
        mealService.deleteMealById(id);
    }
}
