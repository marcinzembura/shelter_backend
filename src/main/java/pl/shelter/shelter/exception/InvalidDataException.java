package pl.shelter.shelter.exception;

import pl.shelter.shelter.accountandrole.account.Account;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}
