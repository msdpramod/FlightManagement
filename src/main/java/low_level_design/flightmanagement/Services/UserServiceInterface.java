package low_level_design.flightmanagement.Services;





import low_level_design.flightmanagement.Models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findByName(String userName);
}
