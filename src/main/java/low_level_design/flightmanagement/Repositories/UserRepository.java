package low_level_design.flightmanagement.Repositories;



import low_level_design.flightmanagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String userName);
}
