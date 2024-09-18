package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.DTOs.FlightDto;
import low_level_design.flightmanagement.Exceptions.InValidArgumentsException;
import low_level_design.flightmanagement.Exceptions.SeatNotAvailableException;
import low_level_design.flightmanagement.Models.*;
import low_level_design.flightmanagement.ObjectMappers.DtoMapper;
import low_level_design.flightmanagement.Repositories.FlightRepository;
import low_level_design.flightmanagement.Repositories.SeatRepository;
import low_level_design.flightmanagement.Repositories.TicketRepository;
import low_level_design.flightmanagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class FlightService implements FlightServiceInterface {
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public FlightService(UserRepository userRepository, FlightRepository flightRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<FlightDto> findFlights(String from, String to, LocalDate departDate, int paxCount) {
        List<Flight_ClearTrip> flights = flightRepository.findFlights(from, to, departDate);
        return flights.stream()
                .filter(flight -> flight.getFares().stream()
                        .anyMatch(fare -> fare.getSeats().stream()
                                .filter(seat -> seat.getType() == SeatType.Available)
                                .count() >= paxCount))
                .map(DtoMapper::mapFlightToDto)
                .collect(Collectors.toList());
    }

    public Ticket bookFlightTicket(List<Long> seatIds, Long userId, Long flightId) throws InValidArgumentsException, SeatNotAvailableException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InValidArgumentsException("User with id: " + userId + " doesn't exist."));

        Flight_ClearTrip flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new InValidArgumentsException("Flight not found for id: " + flightId));

        List<Seat> seats = seatRepository.findAllById(seatIds);
        List<Seat> availableSeats = getAndLockSeats(seats, flight);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setFlight(flight);
        ticket.setSeats(availableSeats);
        ticket.setAmount(0.0);
        ticket.setBookingTime(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<Seat> getAndLockSeats(List<Seat> seats, Flight_ClearTrip flight) throws SeatNotAvailableException {
        for (Seat seat : seats) {
            if (seat.getType() != SeatType.Available) {
                throw new SeatNotAvailableException("Seat with id: " + seat.getId() + " is not available.");
            }
        }

        List<Seat> lockedSeats = new ArrayList<>();
        for (Seat seat : seats) {
            seat.setType(SeatType.Booked);
            lockedSeats.add(seatRepository.save(seat));
        }
        return lockedSeats;
    }

    public List<FlightDto> getAllFlights() {
        List<Flight_ClearTrip> flights = flightRepository.findAll();
        return flights.stream()
                .map(DtoMapper::mapFlightToDto)
                .collect(Collectors.toList());
    }

    public FlightDto findFlightById(Long id) throws InValidArgumentsException {
        Flight_ClearTrip flight = flightRepository.findById(id)
                .orElseThrow(() -> new InValidArgumentsException("Flight not found for id: " + id));
        return DtoMapper.mapFlightToDto(flight);
    }
}