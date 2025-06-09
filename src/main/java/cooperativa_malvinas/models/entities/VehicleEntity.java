package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehiculos")
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Vehiculo", unique = true, nullable = false)
    private Long id;

    @Column(name = "patente", nullable = false)
    private String licensePlate;

    @Column(name = "licencia", nullable = false)
    private String licenseNumber;

    @Column(name = "anio", nullable = false)
    private int year;

    @Column(name = "numero_motor", nullable = false)
    private String engineNumber;

    @Column(name = "numero_chasis", nullable = false)
    private String chassisNumber;

    @ManyToOne
    @JoinColumn(name = "Id_Marca", nullable = false)
    private ModelEntity model;

    @Column(name = "fecha_vencimiento_vtv", nullable = false)
    private LocalDate vtvExpirationDate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MeterTicketEntity> tickets;
}
