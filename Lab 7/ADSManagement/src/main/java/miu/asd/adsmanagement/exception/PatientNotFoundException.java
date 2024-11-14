package miu.asd.adsmanagement.exception;


public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(final String message) {
        super(message);
    }
}
