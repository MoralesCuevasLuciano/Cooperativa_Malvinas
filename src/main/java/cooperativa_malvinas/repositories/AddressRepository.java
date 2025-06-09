package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find an address by street name:
    // List<AddressEntity> findByStreet(String street);
}
