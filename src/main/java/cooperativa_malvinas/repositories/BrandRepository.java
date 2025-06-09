package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a brand by name:
    // Optional<BrandEntity> findByName(String name);
}
