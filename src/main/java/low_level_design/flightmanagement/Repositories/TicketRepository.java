package low_level_design.flightmanagement.Repositories;


import low_level_design.flightmanagement.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
