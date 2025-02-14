package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Usuario;

import ceu.dam.ad.api.user.client.model.User;
import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AppController {

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
		try {
			Usuario user = cliente.login(nombre, passwordCifrada);
			if (user.getTipo().equals("ALUMNO")) {
				cliente.consultarAlumno(user.getIdPerfil());
			} else if (user.getTipo().equals("TUTOR")) {
				cliente.consultarTutor(user.getIdPerfil());
			}
			if (user != null) {
				addParam("usuario", user);
				changeScene(FXML_PANTALLAPRINCIPAL);
				System.out.println("funca");
			}
		} catch (ApiException e) {
			System.out.println(e.getCode());
			System.out.println(e.getLocalizedMessage());
			if (e.getCode() == 0) {
				error("down");
			} else {
				error(e.getResponseBody());

			}

		}
	}

}
