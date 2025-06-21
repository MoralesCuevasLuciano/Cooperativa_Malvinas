package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "settlements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketSettlementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_settlemet", unique = true, nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "vouchers_difference", nullable = false)
    private int vouchersDifference;

    @Column(name = "vouchers_amount", nullable = false)
    private double vouchersAmount;

    @Column(name = "memership_fee_charged", nullable = false)
    private boolean memershipFeeCharged = false;

    @Column(name = "fortnight_closed", nullable = false)
    private boolean fortnightClosed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_member", nullable = false)
    private MemberEntity member;
}
