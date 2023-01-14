package pl.shelter.shelter.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;
import pl.shelter.shelter.medicalCard.MedicalCard;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private MealRepository mealRepository;
    private AnimalRepository animalRepository;

    public MealService(@Autowired MealRepository mealRepository, @Autowired AnimalRepository animalRepository)
    {
        this.mealRepository = mealRepository;
        this.animalRepository=animalRepository;
    }

    public List<Meal> findMealByAnimalId(Integer id) {
        return mealRepository.findMealsByAnimal_Id(id);
    }
    public Iterable<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    public Meal saveMeal(Meal meal){
        return mealRepository.save(meal);
    }
    public void deleteMealById(Integer id){mealRepository.deleteById(id);}
    public void deleteMealByAnimalId(Integer id){

        List<Integer>tmp=mealRepository.getMealIdByAnimalId(id);
        for(int i=0;i<tmp.size();i++){
            System.out.println("wartosci medical:"+tmp.get(i));
            deleteMealById(tmp.get(i));
        }


    }

    public Optional<Meal> findMealById(Integer id) {
        return mealRepository.findById(id);
    }





    public Meal updateMeal(Meal newMeal) {

        System.out.println(newMeal);
        try {
            Optional<Object> updatedMeal = findMealById(newMeal.getId())
                    .map(meal -> {
                        meal.setDate(newMeal.getDate());
                        meal.setName(newMeal.getName());
                        meal.setDescription(newMeal.getDescription());
                        meal.setAnimal(animalRepository.findAnimalById(newMeal.getAnimal().getId()));
                        return mealRepository.save(meal);
                    });
        }catch (Exception e){
            System.out.println(e);
        }
        return newMeal;
    }




    @EventListener(ApplicationReadyEvent.class)
    public void saveRecord() {

//        deleteMealByAnimalId()
//        Meal meal1 = new Meal("lamb", 300, "13.10.2022",animalRepository.findAnimalByName("Puma").get(0));
//        mealRepository.save(meal1);
//        Iterable<Meal> meals = mealRepository.findMealsByAnimal_Name("Puma");
//        meals.forEach(System.out::println);
    }
}
