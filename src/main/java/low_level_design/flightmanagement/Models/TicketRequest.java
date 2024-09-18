package low_level_design.flightmanagement.Models;



import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketRequest {
    private List<UUID> seatIds;
    private UUID userId;
    private UUID flightId;
}
