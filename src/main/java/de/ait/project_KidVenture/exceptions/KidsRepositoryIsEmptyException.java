package de.ait.project_KidVenture.exceptions;

public class KidsRepositoryIsEmptyException extends RuntimeException {
    public KidsRepositoryIsEmptyException() {
        super("No children have registered on your site yet.");
    }

    public KidsRepositoryIsEmptyException(String message) {
        super(message);
    }

    public KidsRepositoryIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public KidsRepositoryIsEmptyException(Throwable cause) {
        super(cause);
    }
}
