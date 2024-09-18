package low_level_design.flightmanagement.Repositories;


import low_level_design.flightmanagement.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findBookingsByUserId(UUID userId);
}
