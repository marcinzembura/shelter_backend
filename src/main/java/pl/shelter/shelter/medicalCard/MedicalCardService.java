package pl.shelter.shelter.medicalCard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;
import pl.shelter.shelter.owner.Owner;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalCardService {

    private MedicalCardRepository medicalCardRepository;
    private AnimalRepository animalRepository;
    public MedicalCardService(@Autowired MedicalCardRepository medicalCardRepository, @Autowired AnimalRepository animalRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.animalRepository=animalRepository;
    }

    public Optional<MedicalCard> findMedicalCardById(Integer id) {
        return medicalCardRepository.findById(id);
    }
    public Iterable<MedicalCard> findAllMedicalCards() {
        return medicalCardRepository.findAll();
    }

    public MedicalCard saveMedicalCard(MedicalCard medicalCard) {
        return medicalCardRepository.save(medicalCard);
    }

    public void deleteMedicalCardById(Integer id) {
        medicalCardRepository.deleteById(id);
    }

    public MedicalCard updateMedicalCard(MedicalCard newMedicalCard) {

        System.out.println(newMedicalCard);
        try {
            Optional<Object> updatedMedicalCard = findMedicalCardById(newMedicalCard.getId())
                    .map(medicalCard -> {
                        medicalCard.setDoctor(newMedicalCard.getDoctor());
                        medicalCard.setDate(newMedicalCard.getDate());
                        medicalCard.setNameOfDisease(newMedicalCard.getNameOfDisease());
                        medicalCard.setDescription(newMedicalCard.getDescription());
                        medicalCard.setAnimal(animalRepository.findAnimalById(newMedicalCard.getAnimal().getId()));
                        return medicalCardRepository.save(medicalCard);
                    });
        }catch (Exception e){
            System.out.println(e);
        }
        return newMedicalCard;
    }



    @EventListener(ApplicationReadyEvent.class)
    public void saveRecord() {
//        MedicalCard medicalCard1 = new MedicalCard("cancer", "13.10.2022", "bla bla bla bla this is cancer",animalRepository.findAnimalByName("Puma").get(0));
//        medicalCardRepository.save(medicalCard1);
//        Iterable<MedicalCard> medicalCards = medicalCardRepository.findMedicalCardByAnimal_Name("Puma");
//        medicalCards.forEach(System.out::println);
    }

}
