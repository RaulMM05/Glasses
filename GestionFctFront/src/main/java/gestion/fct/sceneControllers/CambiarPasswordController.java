package gestion.fct.sceneControllers;

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
	}

	@FXML
	void guardarPassword(ActionEvent event) {
		Alumno alumno = (Alumno) getParam("alumno");
		if (!tpNueva.getText().equals(tpConfirmacion.getText())) {
			error("Las contreña nueva no coincide");
		} else {
			ChangePasswordRequest change = new ChangePasswordRequest();
			change.setOldPassword(DigestUtils.sha256Hex(tpActual.getText()));
			change.setNewPassword(DigestUtils.sha256Hex(tpNueva.getText()));
			try {
				cliente.cambiarPassword(alumno.getId(), change);
			} catch (ApiException e) {
				error(e.getResponseBody());
			}
			lanzarPerfil(event);
		}
	}

	@FXML
	void lanzarPerfil(ActionEvent event) {
		panel.setCenter(loadScene(FXML_PERFIL));
	}

}
