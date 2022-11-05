package pl.shelter.shelter.accountandrole.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
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
