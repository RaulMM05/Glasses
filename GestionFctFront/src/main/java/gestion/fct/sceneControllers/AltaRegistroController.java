package gestion.fct.sceneControllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroRequest;

import gestion.fct.appController.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class AltaRegistroController extends AppController {
    private static final Logger logger = Logger.getLogger(AltaRegistroController.class.getName()); // Instanciamos el logger

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
        logger.info("Cancelando el registro y mostrando detalles.");
        panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
    }

    @FXML
    void guardar(ActionEvent event) {
        logger.info("Intentando guardar el registro.");
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

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    logger.info("Creando el registro en la base de datos...");
                    cliente.crearRegistro(registro);
                    return null;
                }

                @Override
                protected void succeeded() {
                    logger.info("Registro creado con éxito.");
                    panel.setCenter(loadScene(FXML_DETALLESREGISTRO));
                }

                @Override
                protected void failed() {
                    logger.severe("Error al crear el registro: " + getException().getLocalizedMessage());
                    error("Error al crear el registro: " + getException().getLocalizedMessage());
                }
            };
            new Thread(task).start();
        } catch (Exception e) {
            logger.severe("Excepción al guardar el registro: " + e.getLocalizedMessage());
            error(e.getLocalizedMessage());
        }
    }

    void configurarSpinnerHoras() {
        logger.info("Configurando el spinner de horas.");
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
        logger.info("Cargando las fechas disponibles en el ComboBox.");
        try {
            listaFechas = cliente.consultarFecha();
            List<LocalDate> fechas = listaFechas.stream().map(Fecha::getFecha).collect(Collectors.toList());
            ObservableList<LocalDate> observableFechas = FXCollections.observableArrayList(fechas);
            cbFechas.setItems(observableFechas);
        } catch (ApiException e) {
            logger.severe("Error al cargar las fechas: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public Long obtenerId(LocalDate fecha) {
        logger.info("Obteniendo el ID para la fecha: " + fecha);
        Long id = 0L;
        for (Fecha f : listaFechas) {
            if (f.getFecha().equals(fecha)) {
                id = f.getId();
            }
        }
        return id;
    }
}
