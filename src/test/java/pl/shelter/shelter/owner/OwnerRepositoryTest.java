package pl.shelter.shelter.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.shelter.shelter.animal.Animal;
import pl.shelter.shelter.animal.AnimalRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    public void setUp() {
        ownerRepository.deleteAll();
    }

    @Test
    @Disabled
    public void testGetIdOwnerByAnimalId() {
        //given
        Animal testAnimal1=new Animal("Burek", "Samiec", "Pies", 10, 7, true, "1", "1", "29.12.2022");
        animalRepository.save(testAnimal1);
        Owner owner=new Owner("test1",
                "test1","test1","test1","test1","test1", testAnimal1);
        ownerRepository.save(owner);
        //when
        List<Integer> idOwnerByAnimalId = ownerRepository.getIdOwnerByAnimalId(testAnimal1.getId());
        //then

    }


    @Test
    public void testGetIdOwnerByAnimalIdWithInvalidAnimalId() {
        // Given
        int animalId = -1;
        List<Integer> ownerIds = new ArrayList<>();
        Mockito.when(ownerRepository.getIdOwnerByAnimalId(animalId)).thenReturn(ownerIds);

        // When
        List<Integer> result = ownerRepository.getIdOwnerByAnimalId(animalId);

        // Then
        Mockito.verify(ownerRepository).getIdOwnerByAnimalId(animalId);
        assertTrue(result.isEmpty());
    }

    @Test
    @Disabled
    public void testGetIdOwnerByAnimalIdWithNullAnimalId() {
        // Given
        Integer animalId = null;
        List<Integer> ownerIds = new ArrayList<>();
        Mockito.when(ownerRepository.getIdOwnerByAnimalId(animalId)).thenReturn(ownerIds);

        // When
        List<Integer> result = ownerRepository.getIdOwnerByAnimalId(animalId);

        // Then
        Mockito.verify(ownerRepository).getIdOwnerByAnimalId(animalId);
        assertTrue(result.isEmpty());
    }

}