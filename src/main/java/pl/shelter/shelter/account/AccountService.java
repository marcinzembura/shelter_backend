package pl.shelter.shelter.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(@Autowired AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> get(Integer id) {
        return repository.findById(id);
    }

    public Account update(Account entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

//    public Page<Account> list(Pageable pageable) {
//        return repository.findAll(pageable);
//    }

    public int count() {
        return (int) repository.count();
    }

//    @Override
//    public AccountRepository getRepository() {
//        return repository;
//    }
}
