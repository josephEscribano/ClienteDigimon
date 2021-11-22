package gui.controllers.series;

import dao.modelos.ApiError;
import dao.modelos.Digimon;
import dao.modelos.Serie;
import gui.controllers.FXMLPrincipalController;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import service.ServiceDigimon;
import service.ServiceSerie;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLListSerieController implements Initializable {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceSerie serviceSerie;

    @Inject
    private ServiceDigimon serviceDigimon;


    @FXML
    private ListView<Digimon> lvDigimons;
    @FXML
    private ListView<Serie> lvSeries;


    public void laadSeries() {
        var tarea = new Task<Either<ApiError, List<Serie>>>() {
            @Override
            protected Either<ApiError, List<Serie>> call() {
                return serviceSerie.getSeries();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(series -> lvSeries.getItems().setAll(series))
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

    public void laadDigimons() {
        Serie serie = lvSeries.getSelectionModel().getSelectedItem();
        if (serie != null) {
            var tarea = new Task<Either<ApiError, List<Digimon>>>() {
                @Override
                protected Either<ApiError, List<Digimon>> call() {
                    return serviceDigimon.getDigimonsPorSerie(serie.getId());
                }
            };

            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(digimons -> lvDigimons.getItems().setAll(digimons))
                        .peekLeft(apiError -> {
                            lvDigimons.getItems().clear();
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
