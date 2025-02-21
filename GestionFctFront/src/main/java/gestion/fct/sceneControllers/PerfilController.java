package gestion.fct.sceneControllers;

import gestion.fct.appController.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Registro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PerfilController extends AppController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

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
        logger.info("Inicializando perfil de usuario");
        obtenerDatos();
    }

    @FXML
    void lanzarCambiarPass(ActionEvent event) {
        logger.info("Navegando a cambio de contraseña");
        BorderPane panel = (BorderPane) getParam("panel");
        panel.setCenter(loadScene(FXML_CAMBIARPASSWORD));
    }

    @FXML
    void lanzarDetallesRegistros(ActionEvent event) {
        logger.info("Cargando detalles de registros");
        BorderPane panel = (BorderPane) getParam("panel");
        panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    public BigDecimal contarHoras(Alumno alumno) throws ApiException {
        logger.info("Contando horas de registros para el alumno: {}", alumno.getId());
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
            logger.info("Obteniendo datos para el alumno: {}", alumno.getNombreCompleto());
            lblAñoCursado.setText(alumno.getAño().toString());
            lblCurso.setText(alumno.getCiclo());
            lblEmpresa.setText(alumno.getEmpresa().getNombreComercial());
            lblEvaluacion.setText(alumno.getEvaluación());
            BigDecimal horasTotales = new BigDecimal(370);
            BigDecimal horasRealizadas = contarHoras(alumno);
            BigDecimal horasRestantes = horasTotales.subtract(horasRealizadas);
            BigDecimal porcentaje = horasRealizadas.multiply(new BigDecimal(100)).divide(horasTotales, 2, RoundingMode.HALF_UP);
            lblHorasPorRealizar.setText(horasRestantes.toString() + " horas");
            lblHorasRealizadas.setText(horasRealizadas + " horas  " + porcentaje + "%");
            lblHorasTotal.setText(horasTotales.toString() + " horas");
            lblNombre.setText(alumno.getNombreCompleto());
            lblTutorDecente.setText(alumno.getEmpresa().getNombreTutorLaboral());
            logger.info("Datos del alumno cargados correctamente");
        } catch (ApiException e) {
            logger.error("Error obteniendo datos del alumno", e);
        }
    }
}