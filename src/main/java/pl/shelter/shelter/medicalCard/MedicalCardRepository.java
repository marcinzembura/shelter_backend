package pl.shelter.shelter.medicalCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalCardRepository extends CrudRepository<MedicalCard, Integer> {

    List<MedicalCard>findMedicalCardByAnimal_Name(String name);
//    List<MedicalCard>findMedicalCardById(Integer id);


    @Query(nativeQuery = true, value = "SELECT a.id FROM medical_card a WHERE a.id_animal=:idAnimal")
    List<Integer> getIdMedicalCardByAnimalId(Integer idAnimal);



}
