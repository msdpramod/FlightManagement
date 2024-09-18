package low_level_design.flightmanagement.DTOs;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class BookingDto {
    private Long userId;
    private Long flightId;
    private Long fareId;
    private Long seatId;
}