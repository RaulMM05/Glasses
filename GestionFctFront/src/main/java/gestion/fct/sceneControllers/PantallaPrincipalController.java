package gestion.fct.sceneControllers;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PantallaPrincipalController extends AppController {

    private static final Logger logger = LoggerFactory.getLogger(PantallaPrincipalController.class);

    @FXML
    private BorderPane panel;

    public void initialize() {
    	panel.setCenter(loadScene(FXML_PERFIL));
        addParam("panel", panel);
        logger.info("Pantalla principal inicializada");
    }

    @FXML
    void lanzarAltaRegistro(ActionEvent event) {
        logger.info("Cargando pantalla de alta registro");
        panel.setCenter(loadScene(FXML_ALTAREGISTRO));
    }

    @FXML
    void lanzarCerrarApp(ActionEvent event) {
        logger.info("Cerrando aplicación");
        salir();
    }

    @FXML
    void lanzarDetallesRegistro(ActionEvent event) {
        logger.info("Cargando detalles de registro");
        panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void lanzarLogin(ActionEvent event) {
        logger.info("Navegando a pantalla de login");
        changeScene(FXML_LOGIN);
    }

    @FXML
    void lanzarPerfil(ActionEvent event) {
        logger.info("Cargando perfil de usuario");
        panel.setCenter(loadScene(FXML_PERFIL));
    }
}
