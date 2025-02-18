package gestion.fct.sceneControllers;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Registro;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnRegistroController extends AppController{

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
    	panel = (BorderPane) getParam("panel");
    	registro = (Registro) getParam("registro");
    	tfFecha.setText(registro.getFecha().getFecha().toString());
    	tfCantidadDeHoras.setText(registro.getHoras().toString());
    	taDescripcion.setText(registro.getDescripcion());
    }

    @FXML
    void cancelar(ActionEvent event) {
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void borrarRegistro(ActionEvent event) {
    	try {
			cliente.borrarRegistro(registro.getId());
		} catch (ApiException e) {
			error(e.getResponseBody());
		}
    	cancelar(event);
    }

}

