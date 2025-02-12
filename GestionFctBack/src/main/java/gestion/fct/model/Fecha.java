package gestion.fct.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Fecha {
	@Id
	private Long id;	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	@Size(max = 4, min = 4 )
	private Integer añoCurso;
	private String evaluacion;
	
}
