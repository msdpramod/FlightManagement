package low_level_design.flightmanagement.DTOs;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FareDto {
    private String fareType;
    private Double price;
}
