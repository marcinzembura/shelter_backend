package pl.shelter.shelter.medicalCard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;

import java.util.List;

@Service
public class MedicalCardService {

    private MedicalCardRepository medicalCardRepository;
    private AnimalRepository animalRepository;
    public MedicalCardService(@Autowired MedicalCardRepository medicalCardRepository, @Autowired AnimalRepository animalRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.animalRepository=animalRepository;
    }

    public List<MedicalCard> findMedicalCardById(Integer id) {
        return medicalCardRepository.findMedicalCardById(id);
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

    @EventListener(ApplicationReadyEvent.class)
    public void saveRecord() {
//        MedicalCard medicalCard1 = new MedicalCard("cancer", "13.10.2022", "bla bla bla bla this is cancer",animalRepository.findAnimalByName("Puma").get(0));
//        medicalCardRepository.save(medicalCard1);
//        Iterable<MedicalCard> medicalCards = medicalCardRepository.findMedicalCardByAnimal_Name("Puma");
//        medicalCards.forEach(System.out::println);
    }

}
