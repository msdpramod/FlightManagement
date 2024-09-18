package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.Models.Seat;
import low_level_design.flightmanagement.Models.SeatType;
import low_level_design.flightmanagement.Repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements SeatServiceInterface {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> findByType(SeatType type) {
        return seatRepository.findByType(type);
    }
}