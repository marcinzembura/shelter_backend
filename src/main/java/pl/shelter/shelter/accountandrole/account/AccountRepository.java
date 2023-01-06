package pl.shelter.shelter.accountandrole.account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

//    @Query(value = "SELECT u " +
//            "FROM User u " +
//            "WHERE u.role =:user")
//    ArrayList<Account> findEmployeesAcounts();

}

