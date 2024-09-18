package low_level_design.flightmanagement.Services;

import low_level_design.flightmanagement.Exceptions.InValidArgumentsException;
import low_level_design.flightmanagement.Exceptions.SeatNotAvailableException;
import low_level_design.flightmanagement.Models.*;
import low_level_design.flightmanagement.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingService implements BookingServiceInterface {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, FlightRepository flightRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Booking> findBookingsByUserId(UUID userId) {
        return bookingRepository.findBookingsByUserId(userId);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found for the id: " + id));
        bookingRepository.delete(booking);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
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
}