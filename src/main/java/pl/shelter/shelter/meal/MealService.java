package pl.shelter.shelter.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;

@Service
public class MealService {

    private MealRepository mealRepository;
    private AnimalRepository animalRepository;

    public MealService(@Autowired MealRepository mealRepository, @Autowired AnimalRepository animalRepository)
    {
        this.mealRepository = mealRepository;
        this.animalRepository=animalRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveRecord() {
//        Meal meal1 = new Meal("lamb", 300, "13.10.2022",animalRepository.findAnimalByName("Puma").get(3));
//        mealRepository.save(meal1);
//        Iterable<Meal> meals = mealRepository.findMealsByAnimal_Name("Puma");
//        meals.forEach(System.out::println);
    }
}
