package pl.shelter.shelter.medicalCard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;
import pl.shelter.shelter.exception.ApiRequestException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicalCardService {

    private MedicalCardRepository medicalCardRepository;
    private AnimalRepository animalRepository;

    public MedicalCardService(@Autowired MedicalCardRepository medicalCardRepository, @Autowired AnimalRepository animalRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.animalRepository = animalRepository;
    }

    public MedicalCard findMedicalCardById(Integer id) {
        return medicalCardRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find medical card for this ID"));
    }

    public Iterable<MedicalCard> findAllMedicalCards() {
        return medicalCardRepository.findAll();
    }

    public MedicalCard saveMedicalCard(MedicalCard medicalCard) {
        try {
            return medicalCardRepository.save(medicalCard);
        } catch (DataAccessException e) {
            throw new ApiRequestException("Cannot save medicalcard!");
        }
    }


    public void deleteMedicalCardById(Integer id) {
        try {
            medicalCardRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Cannot delete medicalcard for this ID");
        }
    }

    public MedicalCard updateMedicalCard(MedicalCard newMedicalCard) {
        try {
            MedicalCard updatedMedicalCard = findMedicalCardById(newMedicalCard.getId());
            if (updatedMedicalCard != null) {
                updatedMedicalCard.setDoctor(newMedicalCard.getDoctor());
                updatedMedicalCard.setDate(newMedicalCard.getDate());
                updatedMedicalCard.setNameOfDisease(newMedicalCard.getNameOfDisease());
                updatedMedicalCard.setDescription(newMedicalCard.getDescription());
                updatedMedicalCard.setAnimal(animalRepository.findAnimalById(newMedicalCard.getAnimal().getId()));
                medicalCardRepository.save(updatedMedicalCard);
            }
        } catch (Exception e) {
            throw new ApiRequestException("Cannot update medicalcard");
        }
        return newMedicalCard;
    }


    public void deleteMedicalCardByAnimalId(Integer id) {
        List<Integer> tmp = medicalCardRepository.getIdMedicalCardByAnimalId(id);
        for (int i = 0; i < tmp.size(); i++) {
            try {
                deleteMedicalCardById(tmp.get(i));
            } catch (DataAccessException | NoSuchElementException e) {
                throw new ApiRequestException("Cannot delete medicalcard for this ID");
            }
        }
    }
}
