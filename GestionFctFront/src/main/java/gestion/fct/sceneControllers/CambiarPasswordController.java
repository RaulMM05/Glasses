package gestion.fct.sceneControllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.ChangePasswordRequest;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;

public class CambiarPasswordController extends AppController {

    private static final Logger LOGGER = Logger.getLogger(CambiarPasswordController.class.getName());

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnPerfil;

    @FXML
    private PasswordField tpActual;

    @FXML
    private PasswordField tpConfirmacion;

    @FXML
    private PasswordField tpNueva;

    private BorderPane panel;

    @Override
    public void initialize() {
        panel = (BorderPane) getParam("panel");
        LOGGER.info("CambiarPasswordController inicializado");
    }

    @FXML
    void guardarPassword(ActionEvent event) {
        Alumno alumno = (Alumno) getParam("alumno");
        if (!tpNueva.getText().equals(tpConfirmacion.getText())) {
            LOGGER.warning("Las contraseñas no coinciden");
            error("La contraseña nueva no coincide");
        } else {
            ChangePasswordRequest change = new ChangePasswordRequest();
            change.setOldPassword(DigestUtils.sha256Hex(tpActual.getText()));
            change.setNewPassword(DigestUtils.sha256Hex(tpNueva.getText()));
            try {
                LOGGER.info("Intentando cambiar la contraseña para el alumno ID: " + alumno.getId());
                cliente.cambiarPassword(alumno.getId(), change);
                LOGGER.info("Contraseña cambiada con éxito para el alumno ID: " + alumno.getId());
            } catch (ApiException e) {
                LOGGER.log(Level.SEVERE, "Error al cambiar la contraseña", e);
                error(e.getResponseBody());
            }
            lanzarPerfil(event);
        }
    }

    @FXML
    void lanzarPerfil(ActionEvent event) {
        LOGGER.info("Redirigiendo al perfil");
        panel.setCenter(loadScene(FXML_PERFIL));
    }
}
