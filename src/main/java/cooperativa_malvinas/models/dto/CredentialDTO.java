package cooperativa_malvinas.models.dto;

import cooperativa_malvinas.models.enums.CredentialRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@No
public class CredentialDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private CredentialRole role;
}
