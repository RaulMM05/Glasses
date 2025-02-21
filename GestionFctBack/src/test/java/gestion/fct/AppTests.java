package gestion.fct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import gestion.fct.exception.*;
import gestion.fct.model.*;
import gestion.fct.service.UsuarioService;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class AppTests {

    @Autowired
    private UsuarioService service;

    private Usuario usuario;
    private Fecha fecha1;
    private Fecha fecha2;
    private Alumno alumno;

    @BeforeEach
    void initCliente() throws UserNotFoundException, UserUnauthorizedException, UserServiceException, AlumnoNotFoundException {
        usuario = service.login("Pepe", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
        fecha1 = new Fecha(48L, LocalDate.of(2025, 5, 7), 2024, "MARZO");
        fecha2 = new Fecha(49L, LocalDate.of(2025, 5, 8), 2024, "MARZO");
        alumno = service.consultarAlumno(1L);
    }

    // ==============================
    // TESTS PARA VALIDAR FUNCIONALIDAD CORRECTA
    // ==============================

    @Test
    @Order(1)
    void testLoginExitoso() throws UserNotFoundException, UserUnauthorizedException, UserServiceException {
        assertEquals(usuario, service.login("Pepe", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
    }

    @Test
    @Order(2)
    void testCambiarContraseñaExitoso() throws UserNotFoundException, UserUnauthorizedException {
        assertEquals(usuario, service.cambiarContraseña(1L, 
            "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", 
            "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
    }

    @Test
    @Order(3)
    void testCrearRegistroExitoso() throws RegistroServiceException {
    	Registro registro = new Registro();
    	registro.setAlumno(alumno);
    	registro.setDescripcion("Nueva entrada");
    	registro.setFecha(new Fecha(52L, LocalDate.of(2025, 5, 13), 2024, "MARZO"));
    	registro.setHoras(new BigDecimal(4));
    	service.crearRegistro(registro);
    }
    
    @Test
    @Order(4)
    void testConsultarRegistroExitoso() throws AlumnoNotFoundException, RegistroNotFoundException {
        assertEquals(2, service.consultarRegistros(1L, fecha1.getFecha(), fecha2.getFecha()).size());
    }


    @Test
    @Order(5)
    void testBorrarRegistroExitoso() {
    	service.borrarRegistro(1L);
    	service.borrarRegistro(5L);
    }

    // ==============================
    // TESTS PARA MANEJO DE EXCEPCIONES
    // ==============================

    @Test
    @Order(6)
    void testLoginConErrores() {
        assertThrows(UserNotFoundException.class, () -> service.login("pepa", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
        assertThrows(UserUnauthorizedException.class, () -> service.login("Pepe", "contraseñaIncorrecta"));
    }

    @Test
    @Order(7)
    void testCambiarContraseñaConErrores() {
        assertThrows(UserNotFoundException.class, () -> service.cambiarContraseña(99L, "123", "123"));
        assertThrows(UserUnauthorizedException.class, () -> service.cambiarContraseña(1L, "contraseñaIncorrecta", "nuevaContraseña"));
    }

    @Test
    @Order(8)
    void testConsultarRegistroConErrores() {
        LocalDate inicio = LocalDate.now().minusMonths(1L);
        LocalDate fin = LocalDate.now();

        assertThrows(AlumnoNotFoundException.class, () -> service.consultarRegistros(99L, inicio, fin));
        assertThrows(RegistroNotFoundException.class, () -> service.consultarRegistros(1L, inicio, fin));
    }

    @Test
    @Order(9)
    void testCrearRegistroConErrores() {
        Registro registro = new Registro();
        registro.setAlumno(alumno);
        registro.setDescripcion("Prueba error");
        registro.setFecha(fecha1);  // Fecha ya existente en otro registro
        registro.setHoras(new BigDecimal(4));

        assertThrows(RegistroServiceException.class, () -> service.crearRegistro(registro));

        registro.setHoras(new BigDecimal(9));  // Número de horas inválido
        assertThrows(RegistroServiceException.class, () -> service.crearRegistro(registro));

        registro.setHoras(new BigDecimal(0));  // Número de horas inválido
        assertThrows(RegistroServiceException.class, () -> service.crearRegistro(registro));
    }

    @Test
    @Order(10)
    void testConsultarAlumnoConErrores() {
        assertThrows(AlumnoNotFoundException.class, () -> service.consultarAlumno(99L));
    }

    @Test
    @Order(11)
    void testConsultarTutorConErrores() {
        assertThrows(TutorNotFoundException.class, () -> service.consultarTutor(99L));
    }
}
