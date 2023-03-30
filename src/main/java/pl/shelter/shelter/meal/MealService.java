package pl.shelter.shelter.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;
import pl.shelter.shelter.exception.ApiRequestException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MealService {

    private MealRepository mealRepository;
    private AnimalRepository animalRepository;

    public MealService(@Autowired MealRepository mealRepository, @Autowired AnimalRepository animalRepository) {
        this.mealRepository = mealRepository;
        this.animalRepository = animalRepository;
    }

    public List<Meal> findMealByAnimalId(Integer id) {
        try {
            return mealRepository.findMealsByAnimal_Id(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Cannot find meal for this ANIMAL_ID");
        }
    }

    public Iterable<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    public Meal saveMeal(Meal meal) {
        try {
            return mealRepository.save(meal);
        } catch (DataAccessException e) {
            throw new ApiRequestException("Cannot save meal!");
        }
    }

    public void deleteMealById(Integer id) {
        try {
            mealRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Cannot find meal for this ID");
        }
    }

    public void deleteMealByAnimalId(Integer id) {
        List<Integer> tmp = mealRepository.getMealIdByAnimalId(id);
        for (int i = 0; i < tmp.size(); i++) {
            try {
                deleteMealById(tmp.get(i));
            } catch (DataAccessException | NoSuchElementException e) {
                throw new ApiRequestException("Cannot delete meals for this ID");
            }
        }
    }

    public Meal findMealById(Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new ApiRequestException("Cannot find meal for this ID"));
    }

    public Meal updateMeal(Meal newMeal) {

        try {
            Meal mealToUpdate = findMealById(newMeal.getId());
            if (mealToUpdate != null) {
                mealToUpdate.setDate(newMeal.getDate());
                mealToUpdate.setName(newMeal.getName());
                mealToUpdate.setDescription(newMeal.getDescription());
                mealToUpdate.setAnimal(animalRepository.findAnimalById(newMeal.getAnimal().getId()));
                mealRepository.save(mealToUpdate);
            }
        } catch (Exception e) {
            throw new ApiRequestException("Cannot update meal");
        }
        return newMeal;
    }

}
