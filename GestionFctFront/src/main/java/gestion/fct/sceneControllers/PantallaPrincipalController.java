package gestion.fct.sceneControllers;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class PantallaPrincipalController extends AppController{

    @FXML
    private BorderPane panel;

  
	@FXML
    void lanzarAltaRegistro(ActionEvent event) {
    	panel.setCenter(loadScene(FXML_ALTAREGISTRO));
    	
    }

    @FXML
    void lanzarCerrarApp(ActionEvent event) {
    	salir();
    }

    @FXML
    void lanzarDetallesRegistro(ActionEvent event) {
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void lanzarLogin(ActionEvent event) {
    	changeScene(FXML_LOGIN);
    }

    @FXML
    void lanzarPerfil(ActionEvent event) {
    	panel.setCenter(loadScene(FXML_PERFIL));
    	addParam("panel", panel);
    }

}
