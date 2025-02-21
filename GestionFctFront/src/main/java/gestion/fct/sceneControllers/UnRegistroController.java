package gestion.fct.sceneControllers;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Registro;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnRegistroController extends AppController {

    private static final Logger logger = LoggerFactory.getLogger(UnRegistroController.class);

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnCancelar1;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextField tfCantidadDeHoras;

    @FXML
    private TextField tfFecha;

    private BorderPane panel;
    private Registro registro;

    @Override
    public void initialize() {
        logger.info("Inicializando vista de un registro");
        panel = (BorderPane) getParam("panel");
        registro = (Registro) getParam("registro");
        if (registro != null) {
            logger.info("Cargando datos del registro ID: {}", registro.getId());
            tfFecha.setText(registro.getFecha().getFecha().toString());
            tfCantidadDeHoras.setText(registro.getHoras().toString());
            taDescripcion.setText(registro.getDescripcion());
        } else {
            logger.warn("No se encontró un registro para cargar");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        logger.info("Cancelando y regresando a detalles de registros");
        panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void borrarRegistro(ActionEvent event) {
        if (registro != null) {
            try {
                logger.info("Intentando borrar registro ID: {}", registro.getId());
                cliente.borrarRegistro(registro.getId());
                logger.info("Registro ID: {} eliminado con éxito", registro.getId());
            } catch (ApiException e) {
                logger.error("Error al borrar el registro ID: {}", registro.getId(), e);
                error(e.getResponseBody());
            }
        } else {
            logger.warn("Intento de borrar un registro nulo");
        }
        cancelar(event);
    }
}
