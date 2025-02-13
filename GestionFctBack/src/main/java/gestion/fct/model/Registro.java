package gestion.fct.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private BigDecimal horas;
	@NotBlank
	private String descripcion;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Alumno.class)
	@JoinColumn(name = "id_alumno")
	private Alumno alumno;
	@OneToOne
	@JoinColumn(name = "id_fecha")
	private Fecha fecha;
}
