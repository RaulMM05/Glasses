package gestion.fct.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fecha {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull
	private LocalDate fecha;
	@NotNull
	@Size(max = 4, min = 4 )
	private Integer añoCurso;
	@NotBlank
	private String evaluacion;
	
	public static final String MARZO = "MARZO";
	public static final String SEPTIEMBRE = "SEPTIEMBRE";
	
}
