package cooperativa_malvinas.models.entities;

import cooperativa_malvinas.validations.ValidDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "meter_tickets")
@Getter
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation
@ValidDateRange
public class MeterTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", unique = true, nullable = false)
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @PastOrPresent(message = "The start date cannot be in the future")
    @Column(name = "start_date")
    private LocalDate startDate;

    @PastOrPresent(message = "The end date cannot be in the future")
    @Column(name = "cutoff_date")
    private LocalDate cutoffDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "free_km")
    private double freeKm;

    @Column(name = "ocupied_km")
    private double occupiedKm;

    @Column(name = "trips")
    private int trips;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Vehiculo", nullable = false)
    private VehicleEntity vehicle;
}
