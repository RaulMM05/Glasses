package gestion.fct.model.request;

import java.math.BigDecimal;

import gestion.fct.model.Alumno;
import gestion.fct.model.Fecha;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
	
	@NotNull
	private BigDecimal horas;
	@NotBlank
	private String descripcion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alumno")
	private Alumno alumno;
	@OneToOne
	@JoinColumn(name = "id_fecha")
	private Fecha fecha;
}
