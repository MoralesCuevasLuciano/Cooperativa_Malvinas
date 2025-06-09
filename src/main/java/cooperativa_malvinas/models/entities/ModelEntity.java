package cooperativa_malvinas.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Modelo", unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre_modelo", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "Id_Marca", nullable = false)
    private BrandEntity brand;
}
