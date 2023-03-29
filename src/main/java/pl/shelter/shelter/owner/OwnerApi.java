package pl.shelter.shelter.owner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Iterable<Owner> getAll() {
        return ownerService.findAllOwners();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Owner getById(@RequestParam Integer id) {
        return ownerService.findOwnersById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Owner addOwner(@RequestBody Owner owner) {
        System.out.println(owner);
        return ownerService.saveOwner(owner);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public Owner updateOwner(@RequestBody Owner newOwner) {
        return ownerService.updateOwner(newOwner);
    }

    @DeleteMapping(value="/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public void deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwnerById(id);
    }

}
