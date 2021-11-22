package gui.controllers.series;

import dao.modelos.ApiError;
import dao.modelos.Serie;
import gui.controllers.FXMLPrincipalController;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.ServiceSerie;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLAddSerieController implements Initializable {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceSerie serviceSerie;

    @FXML
    private ListView<Serie> listSeries;
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpRelease;


    public void loadSeries() {
        var tarea = new Task<Either<ApiError, List<Serie>>>() {
            @Override
            protected Either<ApiError, List<Serie>> call() {
                return serviceSerie.getSeries();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(series -> listSeries.getItems().setAll(series))
                    .peekLeft(apiError -> {
                        alert.setContentText(apiError.toString());
                        alert.showAndWait();
                    });
            this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.DEFAULT);
        });

        tarea.setOnFailed(workerStateEvent -> {
            alert.setContentText(workerStateEvent.getSource().getException().getMessage());
            alert.showAndWait();
            this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.DEFAULT);
        });

        new Thread(tarea).start();
        this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.WAIT);

    }

    public void addSerie() {
        Serie serie = new Serie();
        serie.setName(tfName.getText());
        serie.setEstreno(dpRelease.getValue());
        var tarea = new Task<Either<ApiError, Serie>>() {
            @Override
            protected Either<ApiError, Serie> call() {
                return serviceSerie.addSerie(serie);
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(serie1 -> listSeries.getItems().add(serie1))
                    .peekLeft(apiError -> {
                        alert.setContentText(apiError.toString());
                        alert.showAndWait();
                    });
            this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.DEFAULT);
        });

        tarea.setOnFailed(workerStateEvent -> {
            alert.setContentText(workerStateEvent.getSource().getException().getMessage());
            alert.showAndWait();
            this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.DEFAULT);
        });

        new Thread(tarea).start();
        this.fxmlPrincipalController.getFxRoot().setCursor(Cursor.WAIT);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
