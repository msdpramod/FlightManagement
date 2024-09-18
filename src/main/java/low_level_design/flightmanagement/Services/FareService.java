package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.Models.Fare;
import low_level_design.flightmanagement.Models.FareType;
import low_level_design.flightmanagement.Repositories.FareRepository;
import low_level_design.flightmanagement.Services.FareServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareService implements FareServiceInterface {
    private final FareRepository fareRepository;

    @Autowired
    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public List<Fare> findByFareType(FareType fareType) {
        return fareRepository.findByFareType(fareType);
    }
}