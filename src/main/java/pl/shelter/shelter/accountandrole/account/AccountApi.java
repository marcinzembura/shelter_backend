package pl.shelter.shelter.accountandrole.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.shelter.shelter.exception.AccountNotFoundException;


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

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getAccountById(@RequestParam Integer id) {
        return accountService.findAccountById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account addAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccountById(id);
    }

}
