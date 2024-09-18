package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.Models.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingServiceInterface {
    List<Booking> findBookingsByUserId(UUID userId);
}