package gestion.fct.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public abstract class Perfil {
	@Id
	private Long id;
	@Size(max = 100)
	@NotNull
	private String nombreCompleto;
}
