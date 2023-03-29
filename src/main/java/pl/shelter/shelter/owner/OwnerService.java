package pl.shelter.shelter.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.animal.AnimalRepository;
import pl.shelter.shelter.exception.ApiRequestException;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Owner findOwnersById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find Owner for this ID"));
    }

    public Owner saveOwner(Owner owner) {
        try {
            animalRepository.findAnimalById(owner.getAnimal().getId()).setStatus(false);
            return ownerRepository.save(owner);
        } catch (DataAccessException e) {
            throw new ApiRequestException("Cannot save owner!");
        }
    }

    public Owner updateOwner(Owner newOwner) {

        System.out.println(newOwner);
        try {
            Owner updatedOwner = findOwnersById(newOwner.getId());
            if (updatedOwner != null) {
                updatedOwner.setPhoneNumber(newOwner.getPhoneNumber());
                updatedOwner.setEmail(newOwner.getEmail());
                updatedOwner.setAddress(newOwner.getAddress());
                updatedOwner.setAnimal(animalRepository.findAnimalById(newOwner.getId()));
                return ownerRepository.save(updatedOwner);
            }
        } catch (Exception e) {
            throw new ApiRequestException("Cannot update owner");
        }
        return newOwner;
    }

    public void deleteOwnerByAnimalId(Integer id) {
        List<Integer> tmp = ownerRepository.getIdOwnerByAnimalId(id);
        for (int i = 0; i < tmp.size(); i++) {
            try {
                deleteOwnerById(tmp.get(i));
            } catch (DataAccessException | NoSuchElementException e) {
                throw new ApiRequestException("Cannot delete owner for this ANIMAL_ID");
            }
        }
    }

    public void deleteOwnerById(Integer id) {
        try {
            ownerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Cannot delete owner for this ID");
        }
    }


}
