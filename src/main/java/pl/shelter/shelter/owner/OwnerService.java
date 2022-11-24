package pl.shelter.shelter.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;

import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;
    private AnimalRepository animalRepository;

    public OwnerService(@Autowired OwnerRepository ownerRepository, @Autowired AnimalRepository animalRepository) {
        this.ownerRepository = ownerRepository;
        this.animalRepository = animalRepository;
    }

    public Iterable<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findOwnersById(Integer id) {
        return ownerRepository.findById(id);
    }

    public Owner saveOwner(Owner owner) {
        animalRepository.findAnimalById(owner.getAnimal().getId()).setStatus(false);
        return ownerRepository.save(owner);
    }

    public void deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getAllRecords() {
//        Owner owner1 = new Owner("Piotrus","Zuchowicz", "767821212", "piotruszuchowicz@gmail.com","12312332112","Krakow",animalRepository.findAnimalByName(id);

//        ownerRepository.save(owner1);
//        Iterable<Owner> owners =  ownerRepository.findAll();
//        owners.forEach(System.out::println);
    }
}
