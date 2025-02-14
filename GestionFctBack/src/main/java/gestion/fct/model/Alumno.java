package gestion.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
	
	public static final String DAM = "DAM";
	public static final String DAW = "DAW";
	public static final String ASIR = "ASIR";
	
	public static final String MARZO = "MARZO";
	public static final String SEPTIEMBRE = "SEPTIEMBRE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 100)
	@NotNull
	private String nombreCompleto;
	private String ciclo;
	private String evaluación;
	private Integer año;
	@ManyToOne
	@JoinColumn(name = "id_tutor")
	private Tutor tutor;
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
}
