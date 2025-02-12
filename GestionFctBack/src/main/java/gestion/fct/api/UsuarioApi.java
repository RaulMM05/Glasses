package gestion.fct.api;

import java.time.LocalDate;
import java.util.List;

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

import gestion.fct.exception.UserNotFound;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizeException;
import gestion.fct.model.Registro;
import gestion.fct.model.Usuario;
import gestion.fct.model.request.ChangePasswordRequest;
import gestion.fct.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("usuario")
@SecurityRequirement(name = "Authorization")
public class UsuarioApi {
	@Autowired
	UsuarioService service;

	@Operation(summary = "Realizar login", description = "Realiza login en la plataforma a partir del nombre de usuario y su contraseña")
	@GetMapping
	public Usuario login(@RequestParam @Size(max = 50) String nombreUsuario,
			@RequestParam @Size(max = 100) String contraseña)
			throws UserNotFound, UserUnauthorizeException, UserServiceException {
		return service.login(nombreUsuario, contraseña);
	}

	@Operation(summary = "Cambiar contraseña", description = "Cambiar contraseña a partir de la ID del usuario y ambas contraseñas en el body")
	@PutMapping("/{id}")
	public Usuario cambiarPassword(@PathVariable Long id,@RequestBody ChangePasswordRequest request) {
//		return service.cambiarContraseña(id, request.getOldPassword(), request.getNewPassword());
		return null;
	}
	@Operation(summary = "Consultar registros", description = "Consulta todos los registros comprendidos entre dos fechas. Si no se pone alguna, por defecto será inicio y fin.")
	@GetMapping("/{id}")
	public List<Registro> consultarRegistros(@PathVariable Long id,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate desde,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate hasta) {
//		return service.consultarRegistros(desde, hasta);
		return null;
	}
	
	@Operation(summary = "Crear registro", description = "Crear un registro completo y lo añade al total de registros de ese alumno")
	@PostMapping
	public Registro crearRegistro(@RequestBody Registro registro) {
//		return service.crearRegistro(registro);
		return null;
	}
	
	@Operation(summary = "Borrar registro", description = "Borra un registro completo y lo elimina del total de registros de ese alumno")
	@DeleteMapping
	public void borrarRegistro(@RequestParam Long id) {
//		service.borrarRegistro(id);
	}

}
