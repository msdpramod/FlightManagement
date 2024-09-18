package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.Models.Fare;
import low_level_design.flightmanagement.Models.FareType;

import java.util.List;

public interface FareServiceInterface {
    List<Fare> findByFareType(FareType fareType);
}