package low_level_design.flightmanagement.Models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private Long id;
}
