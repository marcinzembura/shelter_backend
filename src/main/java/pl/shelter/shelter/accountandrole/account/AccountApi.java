package pl.shelter.shelter.accountandrole.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountApi {

    private AccountService accountService;

    @Autowired
    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }



    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

//    @GetMapping("/all1")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
//    public Iterable<Account> getAllAccounts1() {
////        return accountService.findAllAccounts();
//    }

    @GetMapping
    public Optional<Account> getAccountById(@RequestParam Integer id) {
        return accountService.findAccountById(id);
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PutMapping
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping
    public void deleteAccount(@RequestParam Integer id) {
        accountService.deleteAccountById(id);
    }

}
