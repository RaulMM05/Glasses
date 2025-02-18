package gestion.fct.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.fct.exception.AlumnoNotFoundException;
import gestion.fct.exception.RegistroNotFoundException;
import gestion.fct.exception.RegistroServiceException;
import gestion.fct.exception.TutorNotFoundException;
import gestion.fct.exception.UserNotFoundException;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizedException;
import gestion.fct.model.Alumno;
import gestion.fct.model.Fecha;
import gestion.fct.model.Registro;
import gestion.fct.model.Tutor;
import gestion.fct.model.Usuario;
import gestion.fct.repository.AlumnoRepository;
import gestion.fct.repository.FechasRepository;
import gestion.fct.repository.RegistroRepository;
import gestion.fct.repository.TutorRepository;
import gestion.fct.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository repoUser;
	@Autowired
	private FechasRepository repoFechas;
	@Autowired
	private RegistroRepository repoRegistro;
	@Autowired
	private AlumnoRepository repoAlumno;
	@Autowired
	private TutorRepository repoTutor;

	public Usuario login(String nombreUsuario, String contraseña)
			throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
		logger.info("Intentando login para usuario: {}", nombreUsuario);
		try {
			Usuario usuario = repoUser.findByNombreUsuario(nombreUsuario)
					.orElseThrow(() -> new UserNotFoundException("No existe ningun usuario con ese nombre"));

			if (Boolean.FALSE.equals(usuario.getActivo())) {
				throw new UserServiceException("El usuario no está disponible");
			}
			if (usuario.getTipo().equals(Usuario.ALUMNO)) {
				repoAlumno.findById(usuario.getIdPerfil())
						.orElseThrow(() -> new UserServiceException("No hay ningun alumno asociado a este usuario"));
			}
			if (usuario.getTipo().equals(Usuario.TUTOR)) {
				repoTutor.findById(usuario.getIdPerfil())
						.orElseThrow(() -> new UserServiceException("No hay ningun tutor asociado a este usuario"));
			}

			if (!usuario.getContraseña().equals(contraseña)) {
				throw new UserUnauthorizedException("Contraseña Incorrecta");
			}
			return usuario;
		} catch (Exception e) {
			logger.error("Error en login para usuario: {}", nombreUsuario, e);
			throw e;
		}
	}

	public Usuario cambiarContraseña(Long id, String antiguaContraseña, String nuevaContraseña)
			throws UserNotFoundException, UserUnauthorizedException {
		logger.info("Solicitando cambio de contraseña para usuario con ID: {}", id);
		try {
			Usuario usuario = repoUser.findById(id)
					.orElseThrow(() -> new UserNotFoundException("No existe ningun usuario con esta ID: " + id));

			if (!usuario.getContraseña().equals(antiguaContraseña)) {
				throw new UserUnauthorizedException("Contraseña Incorrecta");
			}
			usuario.setContraseña(nuevaContraseña);
			return repoUser.save(usuario);
		} catch (Exception e) {
			logger.error("Error al cambiar contraseña para usuario con ID: {}", id, e);
			throw e;
		}
	}

	public List<Registro> consultarRegistros(Long id, LocalDate inicio, LocalDate fin)
			throws AlumnoNotFoundException, RegistroNotFoundException {
		logger.info("Consultando registros para ID: {} desde {} hasta {}", id, inicio, fin);
		try {
			Alumno alumno = repoAlumno.findById(id)
					.orElseThrow(() -> new AlumnoNotFoundException("No exite ningun alumno con la ID: " + id));

			List<Fecha> fechas = repoFechas.findByFechaBetween(inicio, fin);
			if (fechas.isEmpty()) {
				throw new RegistroNotFoundException("No existe ningun registro entre las fechas indicadas");
			}

			List<Registro> registros = repoRegistro.findByAlumno(alumno);
			if (registros.isEmpty()) {
				throw new RegistroNotFoundException("No existe ningun registro del alumno indicado");
			}

			registros.removeIf(r -> !fechas.contains(r.getFecha()));

			return registros;
		} catch (Exception e) {
			logger.error("Error al consultar registros para ID: {}", id, e);
			throw e;
		}
	}

	public void borrarRegistro(Long id) {
		logger.info("Eliminando registro con ID: {}", id);
		try {
			repoRegistro.deleteById(id);
		} catch (Exception e) {
			logger.error("Error al eliminar registro con ID: {}", id, e);
			throw e;
		}
	}

	public Registro crearRegistro(Registro registro) throws RegistroServiceException {
		logger.info("Creando nuevo registro para alumno ID: {}", registro.getAlumno().getId());
		try {
			List<Registro> registros = repoRegistro.findByAlumno((Alumno) registro.getAlumno());

			for (Registro r : registros) {
				if (r.getFecha().getFecha().equals(registro.getFecha().getFecha())) {
					throw new RegistroServiceException("Ya existe un registro con esa fecha");
				}
			}

			if (registro.getHoras().compareTo(new BigDecimal("8")) > 0
					|| registro.getHoras().compareTo(new BigDecimal("0")) <= 0) {
				throw new RegistroServiceException("El número de horas no es válido");
			}

			return repoRegistro.save(registro);
		} catch (Exception e) {
			logger.error("Error al crear registro para alumno ID: {}", registro.getAlumno().getId(), e);
			throw e;
		}
	}

	public Alumno consultarAlumno(Long id) throws AlumnoNotFoundException {
		logger.info("Consultando datos del alumno con ID: {}", id);
		try {
			return repoAlumno.findById(id)
					.orElseThrow(() -> new AlumnoNotFoundException("No existe ningun alumno con ID: " + id));
		} catch (Exception e) {
			logger.error("Error al consultar alumno con ID: {}", id, e);
			throw e;
		}
	}

	public Tutor consultarTutor(Long id) throws TutorNotFoundException {
		logger.info("Consultando datos del tutor con ID: {}", id);
		try {
			return repoTutor.findById(id)
					.orElseThrow(() -> new TutorNotFoundException("No existe ningun tutor con ID: " + id));
		} catch (Exception e) {
			logger.error("Error al consultar tutor con ID: {}", id, e);
			throw e;
		}
	}
	
	public Long consultarFecha(LocalDate date) throws UserServiceException {
		logger.info("Consultando id de la fecha: {}", date);
		Fecha fecha =  repoFechas.findByFecha(date);
		if(fecha == null) {
			throw new UserServiceException("Fecha no válida, seleccione otra fecha");
		}
		return fecha.getId();
	}
}
