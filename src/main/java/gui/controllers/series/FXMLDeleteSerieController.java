package gui.controllers.series;

import dao.modelos.ApiError;
import dao.modelos.ApiRespuesta;
import dao.modelos.Serie;
import gui.controllers.FXMLPrincipalController;
import gui.utils.Constantes;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import service.ServiceSerie;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDeleteSerieController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceSerie serviceSerie;

    @FXML
    private ListView<Serie> lvSerie;


    public void loadSeries() {
        var tarea = new Task<Either<ApiError, List<Serie>>>() {
            @Override
            protected Either<ApiError, List<Serie>> call() {
                return serviceSerie.getSeries();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(series -> lvSerie.getItems().setAll(series))
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

    public void deleteSerie() {
        Serie serie = lvSerie.getSelectionModel().getSelectedItem();

        if (serie != null) {
            var tarea = new Task<Either<ApiError, ApiRespuesta>>() {
                @Override
                protected Either<ApiError, ApiRespuesta> call() {
                    return serviceSerie.deleteSerie(serie.getId());
                }
            };

            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(correcto -> {
                            if (correcto.getMessage().equals(Constantes.SERIE_BORRADA)) {
                                lvSerie.getItems().removeIf(digimon1 -> digimon1.getId() == serie.getId());
                            }
                        })
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
        } else {
            alert.setContentText(Constantes.SELECCIONA_UNA_SERIE);
            alert.showAndWait();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
