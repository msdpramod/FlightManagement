package low_level_design.flightmanagement.Models;



import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketRequest {
    private List<Long> seatIds;
    private Long userId;
    private Long flightId;
}
