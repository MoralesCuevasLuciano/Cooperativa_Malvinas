package cooperativa_malvinas.models.dto;

import cooperativa_malvinas.models.entities.AddressEntity;
import cooperativa_malvinas.models.enums.MemberRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDTO {
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    private String firstName;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The second name must be between 2 and 50 characters")
    private String secondName;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The father's surname must be between 2 and 50 characters")
    private String fatherSurname;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "Only letters and spaces are allowed")
    @Size(min = 2, max = 50, message = "The mother's surname must be between 2 and 50 characters")
    private String motherSurname;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "The phone number must contain only digits and be between 10 and 15 characters long")
    @Size(min = 10, max = 15, message = "The phone number must be between 10 and 15 characters long")
    private String phone;

    private AddressEntity address;

    @Pattern(regexp = "^[1-9]\\d{6,7}$", message = "The DNI must have 7 or 8 digits, without leading zeros")
    private String dni;

    @Pattern(regexp = "^[1-9]\\d{9,10}$", message = "The CUIT must have 10 or 11 digits, without leading zeros")
    private String cuit;

    @Past(message = "The birth date cannot be in the future")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @PastOrPresent(message = "The join date cannot be in the future")
    private LocalDate joinDate;

    @PastOrPresent(message = "The leave date cannot be in the future")
    private LocalDate leaveDate;

    private MemberRole role;
}
