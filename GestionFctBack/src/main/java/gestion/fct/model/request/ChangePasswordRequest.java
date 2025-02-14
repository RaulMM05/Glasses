package gestion.fct.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ChangePasswordRequest {

	@NotBlank
	@Size(min = 8)
	private String oldPassword;
	@NotBlank
	@Size(min = 8, message = "La contraseña debe ser de un mínimo de 8 caracteres")
	private String newPassword;

}
