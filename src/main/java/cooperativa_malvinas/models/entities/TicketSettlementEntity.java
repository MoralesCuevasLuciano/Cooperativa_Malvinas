package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "recaudaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketSettlementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Recaudacion", unique = true, nullable = false)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate date;

    @Column(name = "monto_total", nullable = false)
    private double totalAmount;

    @Column(name = "diferencia_vauchers", nullable = false)
    private int vouchersDifference;

    @Column(name = "monto_vauchers", nullable = false)
    private double vouchersAmount;

    @Column(name = "cuota_cobrada", nullable = false)
    private boolean memershipFeeCharged = false;

    @Column(name = "quincena_cerrada", nullable = false)
    private boolean fortnightClosed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Socio", nullable = false)
    private MemberEntity member;
}
