package gestion.fct.sceneControllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroRequest;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AltaRegistroController extends AppController {
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

	private BorderPane panel;

	@Override
	public void initialize() {
		panel = (BorderPane) getParam("panel");
	}

	@FXML
	void cancelar(ActionEvent event) {
		panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
	}

	@FXML
	void guardar(ActionEvent event) {
		Alumno alumno = (Alumno) getParam("alumno");
		RegistroRequest registro = new RegistroRequest();
		Fecha fecha = new Fecha();
		BigDecimal horas = new BigDecimal(tfCantidadDeHoras.getText());
		LocalDate fechaNueva = dpFecha.getValue();
		fecha.setFecha(fechaNueva);
		fecha.setEvaluacion(alumno.getEvaluación());
		fecha.setAñoCurso(alumno.getAño());
		fecha.setId();
		registro.setHoras(horas);
		registro.setDescripcion(taDescripcion.getText());
		registro.setFecha(fecha);
		registro.setAlumno(alumno);
		try {
			cliente.crearRegistro(registro);
		} catch (ApiException e) {
			error(e.getLocalizedMessage());
		}
		panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
	}

}
