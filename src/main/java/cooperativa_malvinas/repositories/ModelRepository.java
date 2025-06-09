package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a model by name:
    // Optional<ModelEntity> findByName(String name);
}
