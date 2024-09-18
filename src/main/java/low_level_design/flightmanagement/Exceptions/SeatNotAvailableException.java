package low_level_design.flightmanagement.Exceptions;

public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}
