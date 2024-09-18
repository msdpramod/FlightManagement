package low_level_design.flightmanagement.Services;





import low_level_design.flightmanagement.Models.Seat;
import low_level_design.flightmanagement.Models.SeatType;

import java.util.List;

public interface SeatServiceInterface {
    List<Seat> findByType(SeatType type);
}