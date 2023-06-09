package pl.shelter.shelter.accountandrole.role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {


    Optional<Role>findByName(ERole role);
}
