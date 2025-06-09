package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.TicketSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSettlementRepository extends JpaRepository <TicketSettlementEntity, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a settlement by ticket ID:
    // Optional<TicketSettlementEntity> findByTicketId(Long ticketId);
}
