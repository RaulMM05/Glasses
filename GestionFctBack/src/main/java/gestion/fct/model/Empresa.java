package gestion.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Empresa {
	@Id
	private Long id;
	private String nombreTutorLaboral;
	@Email
	private String emailTutorLaboral;
	@Size(max = 15)
	private Integer tlfTutorLaboral;
}
