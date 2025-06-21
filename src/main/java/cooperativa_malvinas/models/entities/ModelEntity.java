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
    @Column(name = "id_model", unique = true, nullable = false)
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private BrandEntity brand;
}
