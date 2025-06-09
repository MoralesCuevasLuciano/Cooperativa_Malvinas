package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Direccion", unique = true, nullable = false)
    private Long id;

    @Column(name = "calle", nullable = false)
    private String street;
    @Column(name = "numeral", nullable = false)
    private String numeral;
    @Column(name = "ciudad", nullable = false)
    private String city;
    @Column(name = "piso")
    private String floor;
    @Column(name = "departamento")
    private String apartment;
}
