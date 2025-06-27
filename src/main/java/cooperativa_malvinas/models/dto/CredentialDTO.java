package cooperativa_malvinas.models.dto;

import cooperativa_malvinas.models.enums.CredentialRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private CredentialRole role;
}
