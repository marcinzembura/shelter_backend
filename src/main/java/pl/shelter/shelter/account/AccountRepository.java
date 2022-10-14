package pl.shelter.shelter.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

//    List<Account> findByUsername(String username);
//    List<Account> findByEmail(String email);
//
//    @Query(value = "SELECT u " +
//            "FROM Account u " +
//            "WHERE u.role =:user")
//    ArrayList<Account> findUsersWhoAreClients(@Param("account") String account);
//
//    Page<Account> findAll(Pageable pageable);
}