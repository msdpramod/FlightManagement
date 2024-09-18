package low_level_design.flightmanagement.Repositories;


import low_level_design.flightmanagement.Models.Seat;
import low_level_design.flightmanagement.Models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.type = :type")
    List<Seat> findByType(SeatType type);
}