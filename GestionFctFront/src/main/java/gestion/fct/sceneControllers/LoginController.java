package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginController extends AppController{

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfNombre;

    @FXML
    private PasswordField tpPass;

    @FXML
    public void initialize() {

	}

    @FXML
    void login(ActionEvent event) {
    	String nombre = tfNombre.getText();
    	String passwordCifrada = DigestUtils.sha256Hex(tpPass.getText());
//    	Usuario user = cliente.login(nombre,passwordCifrada);
//    	if(user.getTipo().equals(Usario.ALUMNO)) {
//    		cliente.consultarAlumno(user.getIdPerfil());
//    	}
//    	addParam("usuario", user);
//		changeScene(FXML_PANTALLAPRINCIPAL);
    }

}
