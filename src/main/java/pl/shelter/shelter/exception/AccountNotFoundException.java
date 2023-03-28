package pl.shelter.shelter.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Integer id){
        super("Could not find account: "+ id);
    }
}
