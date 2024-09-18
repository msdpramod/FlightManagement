package low_level_design.flightmanagement.Repositories;


import low_level_design.flightmanagement.Models.Fare;
import low_level_design.flightmanagement.Models.FareType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FareRepository extends JpaRepository<Fare, Long> {
    @Query("SELECT f FROM Fare f WHERE f.type = :fareType")
    List<Fare> findByFareType(FareType fareType);
}
