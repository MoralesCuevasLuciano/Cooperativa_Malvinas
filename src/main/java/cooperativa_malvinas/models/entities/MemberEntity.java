package cooperativa_malvinas.models.entities;

import cooperativa_malvinas.models.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "socios")
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Socio", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Solo se permiten letras y espacios")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre", nullable = false)
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Solo se permiten letras y espacios")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column(name = "apellido", nullable = false)
    private String surname;

    @NotBlank(message = "El apodo no puede estar vacío")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Solo se permiten letras y espacios")
    @Size(min = 2, max = 50, message = "El apodo debe tener entre 2 y 50 caracteres")
    @Column(name = "apodo")
    private String nickname;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[1-9]\\d{6,7}$", message = "El DNI debe tener 7 u 8 dígitos numéricos, sin ceros iniciales")
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @NotBlank(message = "El CUIT no puede estar vacío")
    @Pattern(regexp = "^[1-9]\\d{9,10}$", message = "El CUIT debe tener 10 u 11 dígitos numéricos, sin ceros iniciales")
    @Column(name = "cuit", nullable = false, unique = true)
    private String cuit;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "El teléfono debe tener entre 10 y 15 dígitos numéricos")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    @Column(name = "telefono", nullable = false)
    private String phone;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Fecha_de_nacimiento", nullable = false)
    private LocalDate birthDate;

    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    @Column(name = "Fecha_de_ingreso", nullable = false)
    private LocalDate joinDate;

    @PastOrPresent(message = "La fecha de baja no puede ser futura")
    @Column(name = "Fecha_de_baja")
    private LocalDate leaveDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "Id_Direccion", nullable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<TicketSettlementEntity> settlements;

}
