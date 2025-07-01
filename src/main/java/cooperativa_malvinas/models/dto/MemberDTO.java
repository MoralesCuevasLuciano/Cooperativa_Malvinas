package cooperativa_malvinas.models.dto;

import cooperativa_malvinas.models.entities.AddressEntity;
import cooperativa_malvinas.models.enums.MemberRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDTO {
    @NotBlank(message = "The first name cannot be empty")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    private String firstName;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The second name must be between 2 and 50 characters")
    private String secondName;

    @NotBlank(message = "The father's surname cannot be empty")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The father's surname must be between 2 and 50 characters")
    private String fatherSurname;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The mother's surname must be between 2 and 50 characters")
    private String motherSurname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,15}$", message = "The phone number must contain only digits and be between 10 and 15 characters long")
    @Size(min = 10, max = 15, message = "The phone number must be between 10 and 15 characters long")
    private String phone;

    @NotNull(message = "The address cannot be null")
    private AddressEntity address;

    @NotBlank(message = "The DNI cannot be empty")
    @Pattern(regexp = "^[1-9]\\d{6,7}$", message = "The DNI must have 7 or 8 digits, without leading zeros")
    private String dni;

    @NotBlank(message = "The CUIT cannot be empty")
    @Pattern(regexp = "^[1-9]\\d{9,10}$", message = "The CUIT must have 10 or 11 digits, without leading zeros")
    private String cuit;

    @NotNull(message = "The birth date cannot be empty")
    @Past(message = "The birth date cannot be in the future")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull(message = "The join date cannot be empty")
    @PastOrPresent(message = "The join date cannot be in the future")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate joinDate;

    @NotNull(message = "The role cannot be null")
    private MemberRole role;
}
