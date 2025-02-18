package gestion.fct.sceneControllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Registro;
import org.openapitools.client.model.Usuario;

import gestion.fct.appController.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetallesRegistroController extends AppController {

	@FXML
	private Button btnAlta;

	@FXML
	private Button btnPerfil;

	@FXML
	private TableView<Registro> tbRegistros;

	@FXML
	private TableColumn<Registro, String> tcDescripcion;

	@FXML
	private TableColumn<Registro, LocalDate> tcFecha;

	@FXML
	private TableColumn<Registro, Integer> tcHoras;

	private ObservableList<Registro> datos;

	private BorderPane panel;

	@FXML
	public void initialize() {
		panel = (BorderPane) getParam("panel");
		tcHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
		tcFecha.setCellValueFactory(cellData -> {
			if (cellData.getValue().getFecha() != null) {
				return new SimpleObjectProperty<>(cellData.getValue().getFecha().getFecha());
			}
			return new SimpleObjectProperty<>(null);
		});
		tcDescripcion.setCellValueFactory(cellData -> {
			String descripcion = cellData.getValue().getDescripcion();
			if (descripcion != null && descripcion.length() > 20) {
				descripcion = descripcion.substring(0, 20) + "...";
			}
			return new SimpleStringProperty(descripcion);
		});

		datos = FXCollections.observableArrayList();
		datos.addAll(listaRegistros());
		tbRegistros.setItems(datos);
		lanzarUnRegistro();
	}

	@FXML
	void lanzarAltaRegistro(ActionEvent event) {
		panel.setCenter(loadScene(FXML_ALTAREGISTRO));
	}

	@FXML
	void lanzarPerfil(ActionEvent event) {
		panel.setCenter(loadScene(FXML_PERFIL));
	}

	public List<Registro> listaRegistros() {
		Alumno alumno = (Alumno) getParam("alumno");
		List<Registro> lista = new ArrayList<Registro>();
		try {
			lista = cliente.consultarRegistros(alumno.getId(), null, null);
		} catch (ApiException e) {
			error(e.getResponseBody());
		}
		return lista;
	}

	public void lanzarUnRegistro() {
		tbRegistros.setOnMouseClicked(event -> {
			Registro registroSeleccionado = tbRegistros.getSelectionModel().getSelectedItem();
			addParam("registro", registroSeleccionado);
			if (registroSeleccionado != null) {
				panel.setCenter(loadScene(FXML_UNREGISTRO));
			}

		});
	}

}
