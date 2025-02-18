package gestion.fct.appController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.UsuarioApiApi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import gestion.fct.App;

public class AppController {

	public static final String FXML_ALTAREGISTRO = "scene/AltaRegistro.fxml";
	public static final String FXML_CAMBIARPASSWORD = "scene/CambiarPassword.fxml";
	public static final String FXML_DETALLESREGISTRO = "scene/DetallesRegistro.fxml";
	public static final String FXML_LOGIN = "scene/Login.fxml";
	public static final String FXML_PANTALLAPRINCIPAL = "scene/PantallaPrincipal.fxml";
	public static final String FXML_PERFIL = "scene/Perfil.fxml";
	public static final String FXML_UNREGISTRO = "scene/UnRegistro.fxml";
	public static UsuarioApiApi cliente;
	public static Stage primaryStage;

	public AppController() {

	}

	public AppController(Stage stage) {
		AppController.primaryStage = stage;
	}

	public void initialize() {
		ApiClient client = new ApiClient();
		client.setBasePath("http://localhost:8080");
		client.setApiKey("glasses");
		cliente = new UsuarioApiApi(client);
	}

	public AppController changeScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			return loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Parent loadScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			return scene.getRoot();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addParam(String key, Object param) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		if (mapa == null) {
			mapa = new HashMap<String, Object>();
			primaryStage.setUserData(mapa);
		}
		mapa.put(key, param);
	}

	public Object getParam(String key) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		return mapa.get(key);
	}

	public void salir() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmar salida");
		alerta.setHeaderText(null);
		alerta.setContentText("¿Estas seguro que quieres cerrar la aplicacion?");

		ButtonType respuesta = alerta.showAndWait().orElse(ButtonType.CANCEL);

		if (respuesta == ButtonType.OK) {
			System.exit(0);
		}
	}

	public void error(String error) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error:");
		alerta.setHeaderText(null);
		String serverStatus = "down";
		if (serverStatus.equals(error)) {
			alerta.setContentText("Servidor no responde");
		} else {
			alerta.setContentText(error);
		}

		ButtonType respuesta = alerta.showAndWait().orElse(ButtonType.CANCEL);
	}

}
