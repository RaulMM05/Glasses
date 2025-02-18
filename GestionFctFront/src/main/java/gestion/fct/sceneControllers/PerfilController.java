package gestion.fct.sceneControllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Registro;
import org.openapitools.client.model.Usuario;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PerfilController extends AppController {

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

	public void initialize() {
		obtenerDatos();
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

	public BigDecimal contarHoras(Alumno alumno) throws ApiException {
		List<Registro> lista = cliente.consultarRegistros(alumno.getId(), null, null);
		BigDecimal sumaHoras = new BigDecimal(0);
		for (Registro registro : lista) {
			sumaHoras = sumaHoras.add(registro.getHoras());
		}
		return sumaHoras.setScale(2, RoundingMode.HALF_UP);
	}

	public void obtenerDatos() {
		try {
			Alumno alumno = (Alumno) getParam("alumno");
			lblAñoCursado.setText(alumno.getAño().toString());
			lblCurso.setText(alumno.getCiclo());
			lblEmpresa.setText(alumno.getEmpresa().getNombreComercial());
			lblEvaluacion.setText(alumno.getEvaluación());
			BigDecimal horasTotales = new BigDecimal(370);
			BigDecimal horasRealizadas;
			horasRealizadas = contarHoras(alumno);
			BigDecimal horasRestantes = horasTotales.subtract(horasRealizadas);
			BigDecimal porcentaje = horasRealizadas.multiply(new BigDecimal(100)).divide(horasTotales, 2,
					RoundingMode.HALF_UP);
			lblHorasPorRealizar.setText(horasRestantes.toString() + " horas");
			lblHorasRealizadas.setText(horasRealizadas + " horas  " + porcentaje + "%");
			lblHorasTotal.setText(horasTotales.toString() + " horas");
			lblNombre.setText(alumno.getNombreCompleto());
			lblTutorDecente.setText(alumno.getEmpresa().getNombreTutorLaboral());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
