package low_level_design.flightmanagement.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {
    private String seatType;
    private String room;
    private Integer row;
    private Integer column;
}
