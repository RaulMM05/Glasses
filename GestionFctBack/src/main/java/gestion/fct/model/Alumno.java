package gestion.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Alumno extends Perfil{
	private String ciclo;
	private String evaluación;
	private Integer año;
	@ManyToOne
	private Tutor tutor;
	@OneToOne
	private Empresa empresa;
	
}
