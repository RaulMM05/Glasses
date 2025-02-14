package gestion.fct.sceneControllers;

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

    @FXML
    void cancelar(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));

    }

    @FXML
    void borrarRegistro(ActionEvent event) {
    	
    	cancelar(event);
    }

}

