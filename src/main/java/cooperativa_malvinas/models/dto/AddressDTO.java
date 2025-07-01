package cooperativa_malvinas.models.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "Numeral cannot be empty")
    private String numeral;

    @NotBlank(message = "City cannot be empty")
    private String city;

    private String floor;

    private String apartment;
}
