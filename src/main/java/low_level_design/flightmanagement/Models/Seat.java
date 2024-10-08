package low_level_design.flightmanagement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat extends BaseModel {
    private SeatType type;
    private String room;
    private Integer row;
    private Integer column;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fare fare;

    @ManyToMany(mappedBy = "seats")
    private List<Booking> bookings;
}