package gestion.fct.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.fct.exception.AlumnoNotFoundException;
import gestion.fct.exception.RegistroNotFoundException;
import gestion.fct.exception.RegistroServiceException;
import gestion.fct.exception.UserNotFoundException;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizedException;
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
			throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
		Usuario usuario = repoUser.findByNombreUsuario(nombreUsuario)
				.orElseThrow(() -> new UserNotFoundException("No existe ningun usuario con ese nombre"));

		if (usuario.getActivo() == false) {
			throw new UserServiceException("El usuario no está disponible");
		}
		if (usuario.getPerfilAsociado() == null) {
			throw new UserServiceException("No hay ningun alumno asociado a este usuario");
		}
		if (!usuario.getContraseña().equals(contraseña)) {
			throw new UserUnauthorizedException("Contraseña Incorrecta");
		}
		return usuario;
	}

	public Usuario cambiarContraseña(Long id, String antiguaContraseña, String nuevaContraseña)
			throws UserNotFoundException, UserUnauthorizedException {
		Usuario usuario = repoUser.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No existe ningun usuario con esta ID: " + id));

		if (!usuario.getContraseña().equals(antiguaContraseña)) {
			throw new UserUnauthorizedException("Contraseña Incorrecta");
		}
		usuario.setContraseña(nuevaContraseña);
		return repoUser.save(usuario);

	}

	public List<Registro> consultarRegistros(Long id, LocalDate inicio, LocalDate fin)
			throws AlumnoNotFoundException, RegistroNotFoundException {
		Alumno alumno = repoAlumno.findById(id)
				.orElseThrow(() -> new AlumnoNotFoundException("No exite ningun alumno con la ID: " + id));

		List<Fecha> fechas = repoFechas.findByFechaBetween(inicio, fin);
		if (fechas.isEmpty()) {
			throw new RegistroNotFoundException("No existeb ningun registro entre las fechas indicadas");
		}

		List<Registro> registros = repoRegistro.findByAlumno(alumno);
		if (registros.isEmpty()) {
			throw new RegistroNotFoundException("No existe ningun registro del alumno indicado");
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

	public Registro crearRegistro(Registro registro) throws RegistroServiceException {
		List<Registro> registros = repoRegistro.findByAlumno((Alumno) registro.getAlumno());

		for (Registro r : registros) {
			if (r.getFecha().getFecha().equals(registro.getFecha().getFecha())) {
				throw new RegistroServiceException("Ya existe un registro con esa fecha");
			}
		}

		if (registro.getHoras().compareTo(new BigDecimal("8")) > 0
				|| registro.getHoras().compareTo(new BigDecimal("0")) < 0) {
			throw new RegistroServiceException("El número de horas no es válido");
		}
		
		return repoRegistro.save(registro);
	}

}
