package cooperativa_malvinas.repositories;

import cooperativa_malvinas.models.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByDniAndLeaveDateIsNull(String dni);
    // Custom query methods can be defined here if needed
    // For example, to find a member by name:
    // Optional<MemberEntity> findByName(String name);
}
