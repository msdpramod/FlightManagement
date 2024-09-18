package low_level_design.flightmanagement.Exceptions;


public class InValidArgumentsException extends Exception {
    public InValidArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}