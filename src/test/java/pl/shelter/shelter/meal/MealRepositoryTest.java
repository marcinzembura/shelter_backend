package pl.shelter.shelter.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import pl.shelter.shelter.animal.Animal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MealRepositoryTest {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        mealRepository.deleteAll();
    }

    @Test
    public void testFindMealsByAnimalName() {
        String animalName="testName";
        Animal animal = new Animal(animalName, "Samica", "Pies", 8, 10, false, "1", "1", "29.12.2022");
        entityManager.persist(animal);

        Meal meal1 = new Meal("Meat", "test", "29.12.2022", animal);
        Meal meal2 = new Meal("Fish", "test", "29.12.2022", animal);
        entityManager.persist(meal1);
        entityManager.persist(meal2);

        List<Meal> meals = mealRepository.findMealsByAnimal_Name(animalName);

        assertEquals(2, meals.size());
        assertTrue(meals.contains(meal1));
        assertTrue(meals.contains(meal2));
    }

    @Test
    public void testFindMealsByAnimalId() {
        Animal animal = new Animal("Dog", "Samica", "Pies", 8, 10, false, "1", "1", "29.12.2022");
        entityManager.persist(animal);

        Meal meal1 = new Meal("Meat", "test", "29.12.2022", animal);
        Meal meal2 = new Meal("Fish", "test", "29.12.2022", animal);
        entityManager.persist(meal1);
        entityManager.persist(meal2);

        List<Meal> meals = mealRepository.findMealsByAnimal_Id(animal.getId());

        assertEquals(2, meals.size());
        assertTrue(meals.contains(meal1));
        assertTrue(meals.contains(meal2));
    }

    @Test
    public void testGetMealIdByAnimalId() {
        Animal animal = new Animal("Dog", "Samica", "Pies", 8, 10, false, "1", "1", "29.12.2022");
        entityManager.persist(animal);

        Meal meal1 = new Meal("Meat", "test", "29.12.2022", animal);
        Meal meal2 = new Meal("Fish", "test", "29.12.2022", animal);
        entityManager.persist(meal1);
        entityManager.persist(meal2);

        List<Integer> mealIds = mealRepository.getMealIdByAnimalId(animal.getId());

        assertEquals(2, mealIds.size());
        assertTrue(mealIds.contains(meal1.getId()));
        assertTrue(mealIds.contains(meal2.getId()));
    }
}