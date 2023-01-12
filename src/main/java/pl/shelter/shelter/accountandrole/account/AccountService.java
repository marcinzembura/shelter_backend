package pl.shelter.shelter.accountandrole.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.accountandrole.account.Account;
import pl.shelter.shelter.accountandrole.account.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

     public Iterable<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

//    public Iterable<Account> findAllEmployeesAccounts() {
//        return accountRepository.findAll();
//    }
    public Optional<Account> findAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

    public Account saveAccount(Account account ) {
        return accountRepository.save(account);
    }
}
