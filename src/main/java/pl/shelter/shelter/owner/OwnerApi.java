package pl.shelter.shelter.owner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin
public class OwnerApi {

    private OwnerService ownerService;

    @Autowired
    public OwnerApi(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    public Iterable<Owner> getAll() {
        return ownerService.findAllOwners();
    }

    @GetMapping
    public Optional<Owner> getById(@RequestParam Integer id) {
        return ownerService.findOwnersById(id);
    }

    @PostMapping
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.saveOwner(owner);
    }

    @PutMapping
    public Owner updateOwner(@RequestBody Owner owner) {
        return ownerService.saveOwner(owner);
    }

    @DeleteMapping
    public void deleteOwner(@RequestParam Integer id) {
        ownerService.deleteOwnerById(id);
    }


}
