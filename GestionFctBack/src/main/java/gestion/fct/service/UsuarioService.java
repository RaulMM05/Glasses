package gestion.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
