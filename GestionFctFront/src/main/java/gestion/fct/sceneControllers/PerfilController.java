package gestion.fct.sceneControllers;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PerfilController extends AppController{

    @FXML
    private Button btnCambiarPass;

    @FXML
    private Button btnDetallesRegistros;

    @FXML
    private Label lblAñoCursado;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblEmpresa;

    @FXML
    private Label lblEvaluacion;

    @FXML
    private Label lblHorasPorRealizar;

    @FXML
    private Label lblHorasRealizadas;

    @FXML
    private Label lblHorasTotal;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTutorDecente;
    
    @FXML
    public void initialize() {
    	Usuario user = getParam("usuario");
    	lblAñoCursado.setText(user.getAñoCursado());
    	lblCurso.setText(user.getCurso());
    	lblEmpresa.setText();
    	lblEvaluacion.setText();
    	lblHorasPorRealizar.setText();
    	Integer horasTotales = 100;
        Integer horasRealizadas = 40;
        Integer horasRestantes = horasTotales - horasRealizadas;
        double porcentaje = (horasRealizadas.doubleValue() / horasTotales) * 100;
    	lblHorasRealizadas.setText(horasRestantes + " " + porcentaje + "%");
    	lblHorasTotal.setText();
    	lblNombre.setText();
    	lblTutorDecente.setText();
	}

    @FXML
    void lanzarCambiarPass(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_CAMBIARPASSWORD));
    }

    @FXML
    void lanzarDetallesRegistros(ActionEvent event) {
    	BorderPane panel = (BorderPane) getParam("panel");
    	panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

}
