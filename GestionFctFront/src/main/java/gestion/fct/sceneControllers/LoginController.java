package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Tutor;
import org.openapitools.client.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AppController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfNombre;

    @FXML
    private PasswordField tpPass;

    @FXML
    void login(ActionEvent event) {
        String nombre = tfNombre.getText();
        String passwordCifrada = DigestUtils.sha256Hex(tpPass.getText());
        logger.info("Intento de login para el usuario: {}", nombre);

        try {
            Usuario user = cliente.login(nombre, passwordCifrada);
            if (user != null) {
                logger.info("Usuario autenticado con id: {} con tipo: {}", user.getIdPerfil(), user.getTipo());
                
                if ("ALUMNO".equals(user.getTipo())) {
                    Alumno alumno = cliente.consultarAlumno(user.getIdPerfil());
                    addParam("alumno", alumno);
                    logger.info("Alumno {} autenticado con éxito.", alumno.getNombreCompleto());
                    changeScene(FXML_PANTALLAPRINCIPAL);
                } else if ("TUTOR".equals(user.getTipo())) {
                    Tutor tutor = cliente.consultarTutor(user.getIdPerfil());
                    addParam("tutor", tutor);
                    logger.info("Tutor {} autenticado con éxito.", tutor.getNombreCompleto());
                    changeScene(FXML_PANTALLAPRINCIPAL);
                }
            } else {
                logger.warn("Credenciales incorrectas para usuario: {}", nombre);
            }
        } catch (ApiException e) {
            if (e.getCode() == 0) {
                logger.error("Error de conexión con el servidor", e);
                error("down");
            } else {
                logger.error("Error en la autenticación: {}", e.getResponseBody(), e);
                error(e.getResponseBody());
            }
        }
    }
}