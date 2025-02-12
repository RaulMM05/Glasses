package gestion.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.fct.exception.UserNotFound;
import gestion.fct.exception.UserServiceException;
import gestion.fct.exception.UserUnauthorizeException;
import gestion.fct.model.Usuario;
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

	public Usuario login(String nombreUsuario, String contraseña)
			throws UserNotFound, UserUnauthorizeException, UserServiceException {
		Usuario usuario = repoUser.findByNombreUsuario(nombreUsuario)
				.orElseThrow(() -> new UserNotFound("No existe ningun usuario con ese nombre"));

		if (usuario.getActivo() == false) {
			throw new UserServiceException("El usuario no está disponible");
		}
		if(usuario.getPerfilAsociado() == null) {
			throw new UserServiceException("No hay ningun alumno asociado a este usuario");
		}
		if (!usuario.getContraseña().equals(contraseña)) {
			throw new UserUnauthorizeException("Contraseña Incorrecta");
		}
		return usuario;
	}

}
