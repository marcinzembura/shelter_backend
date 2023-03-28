package pl.shelter.shelter.accountandrole.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.shelter.shelter.accountandrole.account.Account;
import pl.shelter.shelter.accountandrole.account.AccountRepository;
import pl.shelter.shelter.exception.AccountNotFoundException;
import pl.shelter.shelter.exception.InvalidDataException;
import pl.shelter.shelter.exception.RepositoryException;

import java.sql.SQLException;
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

    public Account findAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }


    // !TODO
    public Account updateAccount(Account account) {
        if (validateAccount(account)) {
            try {
                return accountRepository.save(account);
            } catch (Exception e) {
                throw new RuntimeException("Cannot update account");
            }
        }
        return null;
    }

    public void deleteAccountById(Integer id) {
        try {
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            throw new AccountNotFoundException(id);
        }
    }

    private boolean validateAccount(Account account) {
        if (Long.toString(account.getPhone_number()).length() != 9) {
            throw new InvalidDataException("Incorrect phone number");
        } else if (account.getUsername().length() < 3 || account.getUsername().length() > 20) {
            throw new InvalidDataException("Incorrect username");
        }else if(account.getPassword().length() < 6 || account.getUsername().length() > 40){
            throw new InvalidDataException("Incorrect password");
        }
        return true;
    }


    //!TODO
    public Account saveAccount(Account account) {
        if (validateAccount(account)) {
            try {
                return accountRepository.save(account);
            } catch (Exception e) {
                throw new RuntimeException("Cannot save account");
            }
        }
        return null;
    }
}