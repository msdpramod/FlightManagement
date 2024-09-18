package low_level_design.flightmanagement.Controllers;


import low_level_design.flightmanagement.DTOs.FlightDto;
import low_level_design.flightmanagement.DTOs.UserDto;
import low_level_design.flightmanagement.Exceptions.InValidArgumentsException;
import low_level_design.flightmanagement.Models.Ticket;
import low_level_design.flightmanagement.Models.TicketRequest;
import low_level_design.flightmanagement.Services.BookingService;
import low_level_design.flightmanagement.Services.FlightService;
import low_level_design.flightmanagement.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clearTrip")
public class FlightController {
    private final UserService userService;
    private final FlightService flightService;
    private final BookingService bookingService;

    public FlightController(UserService userService, FlightService flightService, BookingService bookingService) {
        this.userService = userService;
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.saveUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/searchFlight")
    public ResponseEntity<List<FlightDto>> searchFlight(@RequestParam String from, @RequestParam String to, @RequestParam LocalDate departDate, @RequestParam int paxCount) {
        List<FlightDto> availableFlights = flightService.findFlights(from, to, departDate, paxCount);
        return new ResponseEntity<>(availableFlights, HttpStatus.OK);
    }

    @PostMapping("/bookFlight")
    public ResponseEntity<Ticket> bookFlightTicket(@RequestBody TicketRequest ticketRequest) {
        try {
            Ticket ticket = flightService.bookFlightTicket(ticketRequest.getSeatIds(), ticketRequest.getUserId(), ticketRequest.getFlightId());
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) throws InValidArgumentsException {
        FlightDto flight = flightService.findFlightById(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/allFlights")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> allFlights = flightService.getAllFlights();
        return new ResponseEntity<>(allFlights, HttpStatus.OK);
    }
}
