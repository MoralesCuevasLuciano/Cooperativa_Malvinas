package cooperativa_malvinas.models.entities;

import cooperativa_malvinas.validations.ValidDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets_reloj")
@Getter
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation
@ValidDateRange
public class MeterTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Ticket", unique = true, nullable = false)
    private Long id;

    @Column(name = "numero_ticket")
    private String ticketNumber;

    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "fecha_emision")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "fecha_corte")
    private LocalDate cutoffDate;

    @Column(name = "importe", nullable = false)
    private double amount;

    @Column(name = "km_libres")
    private double freeKm;

    @Column(name = "km_ocupado")
    private double occupiedKm;

    @Column(name = "viajes")
    private int trips;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Vehiculo", nullable = false)
    private VehicleEntity vehicle;
}
