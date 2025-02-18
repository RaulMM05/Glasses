package gestion.fct.sceneControllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroRequest;

import gestion.fct.appController.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class AltaRegistroController extends AppController {
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnGuardar;

	@FXML
	private Spinner<Double> spinHoras;

	@FXML
	private ComboBox<LocalDate> cbFechas;

	@FXML
	private TextArea taDescripcion;

	private BorderPane panel;

	private List<Fecha> listaFechas;

	@Override
	public void initialize() {
		panel = (BorderPane) getParam("panel");
		configurarSpinnerHoras();
		cargarFechasEnComboBox();
	}

	@FXML
	void cancelar(ActionEvent event) {
		panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
	}

	@FXML
	void guardar(ActionEvent event) {
		try {
			Alumno alumno = (Alumno) getParam("alumno");
			RegistroRequest registro = new RegistroRequest();
			Fecha fecha = new Fecha();
			LocalDate fechaNueva = cbFechas.getValue();
			fecha.setFecha(fechaNueva);
			fecha.setEvaluacion(alumno.getEvaluación());
			fecha.setAñoCurso(alumno.getAño());
			fecha.setId(obtenerId(fechaNueva));
			registro.setHoras(new BigDecimal(spinHoras.getValue()));
			registro.setDescripcion(taDescripcion.getText());
			registro.setFecha(fecha);
			registro.setAlumno(alumno);
			panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
			cliente.crearRegistro(registro);
		} catch (ApiException e) {
			error(e.getLocalizedMessage());
		}
	}

	void configurarSpinnerHoras() {
		SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(
				0.5, 8.0, 0.5, 0.5);
		spinHoras.setValueFactory(valueFactory);
		spinHoras.getEditor().setAlignment(Pos.CENTER);
		spinHoras.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (newValue <= 0) {
					spinHoras.getValueFactory().setValue(0.5);
				} else if (newValue > 8) {
					spinHoras.getValueFactory().setValue(8.0);
				}
			}
		});
	}

	public void cargarFechasEnComboBox() {
		try {
			listaFechas = cliente.consultarFecha();
			System.out.println(listaFechas);
			List<LocalDate> fechas = listaFechas.stream().map(Fecha::getFecha).collect(Collectors.toList());
			ObservableList<LocalDate> observableFechas = FXCollections.observableArrayList(fechas);
			cbFechas.setItems(observableFechas);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	public Long obtenerId(LocalDate fecha) {
		Long id = 0L;
		for (Fecha f : listaFechas) {
			if (f.getFecha().equals(fecha)) {
				id = f.getId();
			}
		}
		return id;
	}

}
