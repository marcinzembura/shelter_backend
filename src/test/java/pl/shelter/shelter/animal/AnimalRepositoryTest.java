package pl.shelter.shelter.animal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;


    @BeforeEach
    public void setUp() {
        animalRepository.deleteAll();
    }

    @Test
    void itShouldFindAnimalById() {
        //given
        Animal animal = new Animal("Dog", "Samica", "Pies", 8, 10, false, "1", "1", "29.12.2022");
        animalRepository.save(animal);
        //when
        Animal animalById = animalRepository.findAnimalById(animal.getId());
        //then
        assertThat(animalById.getId()).isEqualTo(1);
    }

    @Test
    public void testFindAnimalByName() {
        // Given
        String animalName = "test1";
        Animal savedAnimal = new Animal(animalName, "Samica", "Pies", 8, 10, false, "1", "1", "29.12.2022");
        animalRepository.save(savedAnimal);
        // When
        List<Animal> animals = animalRepository.findAnimalByName(animalName);

        // Then
        assertEquals(1, animals.size());
        assertEquals(animalName, animals.get(0).getName());
    }


    @Test
    public void testGetLastSixActiveAnimals() {
        // given
        ArrayList<Animal> expectedAnimals = new ArrayList<>();
        boolean statusActive=true;
        animalRepository.save(new Animal("Max", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Luna", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Charlie", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Molly", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Simba", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Lucy", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Max", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Luna", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Charlie", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Molly", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Simba", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        expectedAnimals.add(new Animal("Lucy", "Samica", "Pies", 8, 10, statusActive, "1", "1", "29.12.2022"));
        Collections.reverse(expectedAnimals);
        AnimalRepository mock = org.mockito.Mockito.mock(AnimalRepository.class);
        when(mock.getLastSixActiveAnimals()).thenReturn(expectedAnimals);

        // when
        List<Animal> actualAnimals = animalRepository.getLastSixActiveAnimals();

        // then
        assertEquals(expectedAnimals.size(), actualAnimals.size());
        for (int i = 0; i < expectedAnimals.size(); i++) {
            Animal expectedAnimal = expectedAnimals.get(i);
            Animal actualAnimal = actualAnimals.get(i);
            assertEquals(expectedAnimal.getName(), actualAnimal.getName());
            assertEquals(expectedAnimal.getTypeOfAnimal(), actualAnimal.getTypeOfAnimal());
            assertEquals(expectedAnimal.getGender(), actualAnimal.getGender());
            assertEquals(expectedAnimal.getAge(), actualAnimal.getAge());
        }
    }


    @Test
    public void testGetNumberOfCats() {
        // given
        int expectedNumber = 3;
        animalRepository.save(new Animal("Mimi", "Samica", "Kot", 2, 5, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Filemon", "Samiec", "Kot", 5, 7, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Mruczek", "Samiec", "Kot", 4, 3, true, "1", "1", "29.12.2022"));

        // when
        int actualNumber = animalRepository.getNumberOfCats();

        // then
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void testGetNumberOfDogs() {
        // given
        int expectedNumber = 1;
        animalRepository.save(new Animal("Burek", "Samiec", "Pies", 10, 7, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Azor", "Samiec", "Kot", 3, 4, true, "1", "1", "29.12.2022"));

        // when
        int actualNumber = animalRepository.getNumberOfDogs();

        // then
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void testGetNumberOfMales() {
        // given
        int expectedNumber = 2;
        animalRepository.save(new Animal("Burek", "Samiec", "Pies", 10, 7, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Filemon", "Samiec", "Kot", 5, 7, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Lola", "Samica", "Kot", 3, 4, true, "1", "1", "29.12.2022"));

        // when
        int actualNumber = animalRepository.getNumberOfMales();

        // then
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void testGetNumberOfFemales() {
        // given
        int expectedNumber = 1;
        animalRepository.save(new Animal("Mimi", "Samiec", "Kot", 2, 5, true, "1", "1", "29.12.2022"));
        animalRepository.save(new Animal("Azor", "Samica", "Kot", 3, 4, true, "1", "1", "29.12.2022"));

        // when
        int actualNumber = animalRepository.getNumberOfFemales();

        // then
        assertEquals(expectedNumber, actualNumber);
    }

    @AfterEach
    public void tearDown() {
        // delete all animals from database
        animalRepository.deleteAll();
    }
}


