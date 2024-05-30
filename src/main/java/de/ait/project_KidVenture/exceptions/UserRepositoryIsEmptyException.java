package de.ait.project_KidVenture.exceptions;

public class UserRepositoryIsEmptyException extends RuntimeException {
    public UserRepositoryIsEmptyException() {
        super("No children have registered on your site yet.");
    }

    public UserRepositoryIsEmptyException(String message) {
        super(message);
    }

    public UserRepositoryIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepositoryIsEmptyException(Throwable cause) {
        super(cause);
    }
}
