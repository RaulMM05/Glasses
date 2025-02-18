package gestion.fct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
import gestion.fct.service.UsuarioService;

@SpringBootTest
@ActiveProfiles("test")
class AppTests {
	
	Usuario usuario;
	Fecha fecha1;
	Fecha fecha2;
	Alumno alumno;
	Tutor tutor;
	
	@BeforeEach
	void initCliente() throws UserNotFoundException, UserUnauthorizedException, UserServiceException, AlumnoNotFoundException, TutorNotFoundException {
		usuario = service.login("Pepe", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
		fecha1 = new Fecha(48L, LocalDate.of(2025, 05, 7), 2024, "MARZO");
		fecha2 = new Fecha(49L, LocalDate.of(2025, 05, 8), 2024, "MARZO");
		alumno = service.consultarAlumno(1L);
		tutor = service.consultarTutor(1L);
	}
	
	@Autowired
	private UsuarioService service;
	
	@Test
	void testLogin() throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
		assertThrows(UserNotFoundException.class, () -> service.login("pepa", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
		assertThrows(UserServiceException.class, () -> service.login("Juan", "123"));
		assertThrows(UserUnauthorizedException.class, () -> service.login("Pepe", "1234"));
		
		assertEquals(usuario, service.login("Pepe", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
	}
	@Test
	void testCambiarContraseña() throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
		Usuario usuario = service.login("Pepe", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
		assertThrows(UserNotFoundException.class,() -> service.cambiarContraseña(5L, "123", "123"));
		assertThrows(UserUnauthorizedException.class,() -> service.cambiarContraseña(1L, "123", "123"));
		assertEquals(usuario, service.cambiarContraseña(1L, "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
	}
	@Test
	void testConsultarRegistro() throws AlumnoNotFoundException, RegistroNotFoundException {
		LocalDate inicio = LocalDate.now().minusMonths(1L);
		LocalDate fin = LocalDate.now();
		assertThrows(AlumnoNotFoundException.class, () -> service.consultarRegistros(5L, inicio, fin));
		assertThrows(RegistroNotFoundException.class, () -> service.consultarRegistros(1L, inicio, fin));
		assertEquals(2 , service.consultarRegistros(1L, fecha1.getFecha(), fecha2.getFecha()).size());
	}
	@Test
	void testBorrarRegistro() {
		service.borrarRegistro(1L);
		service.borrarRegistro(5L);
	}
	
	@Test
	void testCrearRegistro() throws RegistroServiceException {
		Registro registro = new Registro();
		registro.setAlumno(alumno);
		registro.setDescripcion("aaa");
		registro.setFecha(fecha1);
		registro.setHoras(new BigDecimal(4));
		registro.setId(1L);
		
		assertThrows(RegistroServiceException.class, () -> service.crearRegistro(registro));
		
		registro.setHoras(new BigDecimal(9));
		assertThrows(RegistroServiceException.class, () -> service.crearRegistro(registro));
		registro.setHoras(new BigDecimal(4));
		//Si lo vas a correr varias veces el testing, debes cambiar el valor del Long de la fecha, así como el día del LocalDate
		registro.setFecha(new Fecha(52L, LocalDate.of(2025, 05, 13), 2024, "MARZO"));
		registro.setId(null);
		service.crearRegistro(registro);
	}
	
	@Test
	void consultarAlumno() throws AlumnoNotFoundException {
		assertThrows(AlumnoNotFoundException.class,() -> service.consultarAlumno(5L));
		assertEquals(1L, service.consultarAlumno(1L).getId());
	}
	
	@Test
	void consultarTutor() throws TutorNotFoundException {
		assertThrows(TutorNotFoundException.class,() -> service.consultarTutor(2L));
		assertEquals(1L, service.consultarTutor(1L).getId());
	}
}
