package pl.shelter.shelter.exception;

import java.sql.SQLException;

public class RepositoryException extends RuntimeException{

    public RepositoryException(String msg, SQLException ex ){
        super(msg,ex);
    }
}
