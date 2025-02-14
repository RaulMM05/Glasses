package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class CambiarPasswordController extends AppController{

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnPerfil;

    @FXML
    private Label lblSeguridad;

    @FXML
    private ProgressBar pbBarraSeguridad;

    @FXML
    private PasswordField tpActual;

    @FXML
    private PasswordField tpConfirmacion;

    @FXML
    private PasswordField tpNueva;

    @FXML
    void guardarPassword(ActionEvent event) {
    	Usuario user = getParam("usuario");
    	if(!tpNueva.getText().equals(tpConfirmacion.getText())) {
    		error("Las contreña nueva no coincide");
    	}else {    		
    	String passwordActual = DigestUtils.sha256Hex(tpActual.getText());
    	String passwordNueva = DigestUtils.sha256Hex(tpNueva.getText());
    	cliente.cambiarContraseña(passwordActual,passwordNueva,user.getId());
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_PERFIL));
    	}
    }

    @FXML
    void lanzarPerfil(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_PERFIL));
    }

    @FXML
    void registroPulsacion(KeyEvent event) {

    }

}
