package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.DTOs.FlightDto;

import java.time.LocalDate;
import java.util.List;

public interface FlightServiceInterface {
    List<FlightDto> findFlights(String from, String to, LocalDate departDate, int paxCount);
}