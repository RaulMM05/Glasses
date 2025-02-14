package gestion.fct.sceneControllers;

import java.time.LocalDate;

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

public class DetallesRegistroController extends AppController{

    @FXML
    private Button btnAlta;

    @FXML
    private Button btnPerfil;

    @FXML
    private TableView<Usuario> tbRegistros;

    @FXML
    private TableColumn<Usuario, String> tcDescripcion;

    @FXML
    private TableColumn<Usuario, LocalDate> tcFecha;

    @FXML
    private TableColumn<Usuario, Integer> tcHoras;
    
    private ObservableList<Usuario> datos;
    
    
    @FXML
	public void initialize() {
    	tcHoras.setCellValueFactory(new PropertyValueFactory<>("Horas"));
    	tcFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
    	tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripción"));

		datos = FXCollections.observableArrayList();
		tbRegistros.setItems(datos);
	}

    @FXML
    void lanzarAltaRegistro(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_ALTAREGISTRO));
    }

    @FXML
    void lanzarPerfil(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_PERFIL));
    }

}
