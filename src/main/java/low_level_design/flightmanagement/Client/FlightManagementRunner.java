package low_level_design.flightmanagement.Client;

import low_level_design.flightmanagement.DTOs.FlightDto;
import low_level_design.flightmanagement.Models.Ticket;
import low_level_design.flightmanagement.Models.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class FlightManagementRunner implements CommandLineRunner {
    private final FlightManagementClient client;

    @Autowired
    public FlightManagementRunner(FlightManagementClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        // Get all flights
        List<FlightDto> flights = client.getAllFlights();
        flights.forEach(System.out::println);

        // Get a flight by ID
        Long flightId = 1L; // Replace with an actual flight ID
        FlightDto flight = client.getFlightById(flightId);
        System.out.println(flight);

        //Book a flight ticket
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setSeatIds(Arrays.asList(1L, 2L)); // Replace with actual seat IDs
        ticketRequest.setUserId(1L); // Replace with an actual user ID
        ticketRequest.setFlightId(flightId);
        Ticket ticket = client.bookFlightTicket(ticketRequest);
        System.out.println(ticket);
    }
}