package low_level_design.flightmanagement.Client;


import low_level_design.flightmanagement.DTOs.FlightDto;
import low_level_design.flightmanagement.Models.Ticket;
import low_level_design.flightmanagement.Models.TicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class FlightManagementClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080"; // Adjust the base URL as needed

    public FlightManagementClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FlightDto> getAllFlights() {
        String url = baseUrl + "/allFlights";
        ResponseEntity<List<FlightDto>> response = restTemplate.getForEntity(url, (Class<List<FlightDto>>)(Class<?>)List.class);
        return response.getBody();
    }

    public FlightDto getFlightById(UUID id) {
        String url = baseUrl + "/flights/" + id;
        ResponseEntity<FlightDto> response = restTemplate.getForEntity(url, FlightDto.class);
        return response.getBody();
    }

    public Ticket bookFlightTicket(TicketRequest ticketRequest) {
        String url = baseUrl + "/bookFlight";
        ResponseEntity<Ticket> response = restTemplate.postForEntity(url, ticketRequest, Ticket.class);
        return response.getBody();
    }
}
