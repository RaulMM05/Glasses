package gestion.fct.sceneControllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AltaRegistroController extends AppController{
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextField tfCantidadDeHoras;

    @FXML
    void cancelar(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void fecha(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
    	RegistroRequest registo = new RegistroRequest();
    	Fecha fecha = new Fecha();
    	BigDecimal horas = new BigDecimal(tfCantidadDeHoras.getText());
    	LocalDate fechaNueva = dpFecha.getValue();
    	fecha.setFecha(fechaNueva);
    	registro.setHoras(horas);
    	registro.setDescripcion(taDescripcion.getText());
    	registro.setFecha(fecha);
    	cliente.altaRegistro(registro);
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

}
