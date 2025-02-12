package gestion.fct.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.fct.exception.AlumnoNotFound;
import gestion.fct.exception.RegistroNotFounException;
import gestion.fct.exception.UserNotFound;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizeException;
import gestion.fct.model.Alumno;
import gestion.fct.model.Fecha;
import gestion.fct.model.Registro;
import gestion.fct.model.Usuario;
import gestion.fct.repository.AlumnoRepository;
import gestion.fct.repository.FechasRepository;
import gestion.fct.repository.RegistroRepository;
import gestion.fct.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repoUser;
	@Autowired
	private FechasRepository repoFechas;
	@Autowired
	private RegistroRepository repoRegistro;
	@Autowired
	private AlumnoRepository repoAlumno;

	public Usuario login(String nombreUsuario, String contraseña)
			throws UserNotFound, UserUnauthorizeException, UserServiceException {
		Usuario usuario = repoUser.findByNombreUsuario(nombreUsuario)
				.orElseThrow(() -> new UserNotFound("No existe ningun usuario con ese nombre"));

		if (usuario.getActivo() == false) {
			throw new UserServiceException("El usuario no está disponible");
		}
		if (usuario.getPerfilAsociado() == null) {
			throw new UserServiceException("No hay ningun alumno asociado a este usuario");
		}
		if (!usuario.getContraseña().equals(contraseña)) {
			throw new UserUnauthorizeException("Contraseña Incorrecta");
		}
		return usuario;
	}

	public Usuario cambiarContraseña(Long id, String antiguaContraseña, String nuevaContraseña)
			throws UserNotFound, UserUnauthorizeException {
		Usuario usuario = repoUser.findById(id)
				.orElseThrow(() -> new UserNotFound("No existe ningun usuario con esta ID: " + id));

		if (!usuario.getContraseña().equals(antiguaContraseña)) {
			throw new UserUnauthorizeException("Contraseña Incorrecta");
		}
		usuario.setContraseña(nuevaContraseña);
		return repoUser.save(usuario);

	}

	public List<Registro> consultarRegistros(Long id, LocalDate inicio, LocalDate fin)
			throws AlumnoNotFound, RegistroNotFounException {
		Alumno alumno = repoAlumno.findById(id)
				.orElseThrow(() -> new AlumnoNotFound("No exite ningun alumno con la ID: " + id));

		List<Fecha> fechas = repoFechas.findByFechaBetween(inicio, fin);
		if (fechas.isEmpty()) {
			throw new RegistroNotFounException("No existeb ningun registro entre las fechas indicadas");
		}

		List<Registro> registros = repoRegistro.findByAlumno(alumno);
		if (registros.isEmpty()) {
			throw new RegistroNotFounException("No existe ningun registro del alumno indicado");
		}

		for (Registro r : registros) {
			if (!fechas.contains(r.getFecha())) {
				registros.remove(r);
			}
		}

		return registros;
	}
	
	public void borrarRegistro(Long id) {
		repoRegistro.deleteById(id);
		
	}

}
