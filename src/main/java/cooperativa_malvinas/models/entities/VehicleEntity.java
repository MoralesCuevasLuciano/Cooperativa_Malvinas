package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle", unique = true, nullable = false)
    private Long id;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "engine_number", nullable = false)
    private String engineNumber;

    @Column(name = "chassis_number", nullable = false)
    private String chassisNumber;

    @ManyToOne
    @JoinColumn(name = "id_model", nullable = false)
    private ModelEntity model;

    @Column(name = "vtv_expiration_date", nullable = false)
    private LocalDate vtvExpirationDate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MeterTicketEntity> tickets;
}
