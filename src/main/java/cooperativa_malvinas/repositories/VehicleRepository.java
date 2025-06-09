package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a vehicle by license plate:
    // Optional<VehicleEntity> findByLicensePlate(String licensePlate);
}
