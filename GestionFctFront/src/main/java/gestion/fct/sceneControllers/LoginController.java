package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Tutor;
import org.openapitools.client.model.Usuario;

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
		String nombre = tfNombre.getText();//tpPass.getText()
		String passwordCifrada = DigestUtils.sha256Hex("1234");
		try {//nombre
			Usuario user = cliente.login("pepe", passwordCifrada);
			if (user != null) {
				if (user.getTipo().equals("ALUMNO")) {
					Alumno alumno = cliente.consultarAlumno(user.getIdPerfil());
					addParam("alumno", alumno);
					changeScene(FXML_PANTALLAPRINCIPAL);
				} else if (user.getTipo().equals("TUTOR")) {
					Tutor tutor = cliente.consultarTutor(user.getIdPerfil());
					addParam("tutor", tutor);
					changeScene(FXML_PANTALLAPRINCIPAL);
				}
			}
		} catch (ApiException e) {
			if (e.getCode() == 0) {
				error("down");
			} else {
				error(e.getResponseBody());

			}

		}
	}

}
