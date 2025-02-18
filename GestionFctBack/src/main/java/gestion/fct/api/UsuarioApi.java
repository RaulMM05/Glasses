package gestion.fct.api;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestion.fct.exception.AlumnoNotFoundException;
import gestion.fct.exception.RegistroNotFoundException;
import gestion.fct.exception.RegistroServiceException;
import gestion.fct.exception.TutorNotFoundException;
import gestion.fct.exception.UserNotFoundException;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizedException;
import gestion.fct.model.Alumno;
import gestion.fct.model.Registro;
import gestion.fct.model.Tutor;
import gestion.fct.model.Usuario;
import gestion.fct.model.request.ChangePasswordRequest;
import gestion.fct.model.request.RegistroRequest;
import gestion.fct.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("usuario")
@SecurityRequirement(name = "Authorization")
public class UsuarioApi {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioApi.class);

	@Autowired
	UsuarioService service;

	@Operation(summary = "Realizar login", description = "Realiza login en la plataforma a partir del nombre de usuario y su contraseña")
	@GetMapping
	public Usuario login(@RequestParam @Size(max = 50) String nombreUsuario,
			@RequestParam @Size(max = 100) String contraseña)
			throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
		logger.info("Intentando login para usuario: {}", nombreUsuario);
		return service.login(nombreUsuario, contraseña);
	}

	@Operation(summary = "Cambiar contraseña", description = "Cambiar contraseña a partir de la ID del usuario y ambas contraseñas en el body")
	@PutMapping("/{id}")
	public Usuario cambiarPassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordRequest request)
			throws UserNotFoundException, UserUnauthorizedException {
		logger.info("Solicitando cambio de contraseña para usuario con ID: {}", id);
		return service.cambiarContraseña(id, request.getOldPassword(), request.getNewPassword());
	}

	@Operation(summary = "Consultar registros", description = "Consulta todos los registros comprendidos entre dos fechas.")
	@GetMapping("/{id}")
	public List<Registro> consultarRegistros(@PathVariable Long id,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate desde,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hasta)
			throws AlumnoNotFoundException, RegistroNotFoundException {
		if (desde == null) {
			desde = LocalDate.of(1999, 1, 1);
		}
		if (hasta == null) {
			hasta = LocalDate.of(2100, 12, 30);
		}
		logger.info("Consultando registros para ID: {} desde {} hasta {}", id, desde, hasta);
		return service.consultarRegistros(id, desde, hasta);
	}

	@Operation(summary = "Crear registro", description = "Crear un registro completo y lo añade al total de registros de ese alumno")
	@PostMapping
	public Registro crearRegistro(@RequestBody RegistroRequest registro) throws RegistroServiceException {
		logger.info("Creando nuevo registro:");
		Registro regist = new Registro();
		ModelMapper mapper = new ModelMapper();
		mapper.map(registro, regist);
		return service.crearRegistro(regist);
	}

	@Operation(summary = "Borrar registro", description = "Borra un registro completo y lo elimina del total de registros de ese alumno")
	@DeleteMapping
	public void borrarRegistro(@RequestParam Long id) {
		logger.info("Eliminando registro con ID: {}", id);
		service.borrarRegistro(id);
	}

	@Operation(summary = "Consultar alumno", description = "Consulta todos los datos de un alumno.")
	@GetMapping("/alumno/{id}")
	public Alumno consultarAlumno(@PathVariable Long id) throws AlumnoNotFoundException {
		logger.info("Consultando datos del alumno con ID: {}", id);
		return service.consultarAlumno(id);
	}

	@Operation(summary = "Consultar tutor", description = "Consulta todos los datos de un tutor.")
	@GetMapping("/tutor/{id}")
	public Tutor consultarTutor(@PathVariable Long id) throws TutorNotFoundException {
		logger.info("Consultando datos del tutor con ID: {}", id);
		return service.consultarTutor(id);
	}
	
	@Operation(summary = "Consultar fecha", description = "Consulta la ID de la fecha que se recibe por parámetros.")
	@GetMapping("/fecha")
	public Long consultarFecha(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
		System.out.println(fecha);
		logger.info("Consultando la ID de la fecha recibida: ", fecha);
		return service.consultarFecha(fecha);
	}
}