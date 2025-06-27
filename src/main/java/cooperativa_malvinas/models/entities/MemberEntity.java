package cooperativa_malvinas.models.entities;

import cooperativa_malvinas.models.enums.MemberRole;
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
@Table(name = "members")
@Data // Includes getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // No-args constructor for JPA
@AllArgsConstructor // All-args constructor for convenience
@Builder // Builder pattern for easy object creation
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The second name must be between 2 and 50 characters")
    @Column(name = "second_name", nullable = false)
    private String secondName;

    @NotBlank(message = "The father's surname cannot be empty")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The surname must be between 2 and 50 characters")
    @Column(name = "father_surname", nullable = false)
    private String fatherSurname;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The nickname must be between 2 and 50 characters")
    @Column(name = "mother_surname")
    private String motherSurname;

    @NotBlank(message = "The DNI cannot be empty")
    @Pattern(regexp = "^[1-9]\\d{6,7}$", message = "The DNI must have 7 or 8 digits, without leading zeros")
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @NotBlank(message = "The CUIT cannot be empty")
    @Pattern(regexp = "^[1-9]\\d{9,10}$", message = "The CUIT must have 10 or 11 digits, without leading zeros")
    @Column(name = "cuit", nullable = false, unique = true)
    private String cuit;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "The phone number must contain only digits and be between 10 and 15 characters long")
    @Size(min = 10, max = 15, message = "The phone number must be between 10 and 15 characters long")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "The email cannot be empty")
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "The birth date cannot be empty")
    @Past(message = "The birth date cannot be in the future")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "The join date cannot be empty")
    @PastOrPresent(message = "The join date cannot be in the future")
    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @PastOrPresent(message = "The leave date cannot be in the future")
    @Column(name = "leave_date")
    private LocalDate leaveDate;

    @NotNull(message = "The role cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;

    @ManyToOne
    @JoinColumn(name = "id_address", nullable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<TicketSettlementEntity> settlements;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CredentialEntity credential;

}
