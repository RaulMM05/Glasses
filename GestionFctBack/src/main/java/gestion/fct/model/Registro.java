package gestion.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Registro {
	@Id
	private Long id;
	private Integer horas;
	private String descripcion;
	@ManyToOne
	private Alumno alumno;
	@OneToOne
	private Fecha fecha;
}
