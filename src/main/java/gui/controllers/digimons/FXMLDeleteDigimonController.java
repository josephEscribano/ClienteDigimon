package gui.controllers.digimons;

import dao.modelos.ApiError;
import dao.modelos.ApiRespuesta;
import dao.modelos.Digimon;
import gui.controllers.FXMLPrincipalController;
import gui.utils.Constantes;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import service.ServiceDigimon;

import javax.inject.Inject;
import java.util.List;

public class FXMLDeleteDigimonController {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceDigimon serviceDigimon;
    @FXML
    private ListView<Digimon> lvDigimon;


    public void loadDigimon() {
        var tarea = new Task<Either<ApiError, List<Digimon>>>() {
            @Override
            protected Either<ApiError, List<Digimon>> call() {
                return serviceDigimon.getAllDigimons();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(digimons -> lvDigimon.getItems().setAll(digimons))
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

    public void deleteDigimon() {
        Digimon digimon = lvDigimon.getSelectionModel().getSelectedItem();

        if (digimon != null) {
            var tarea = new Task<Either<ApiError, ApiRespuesta>>() {
                @Override
                protected Either<ApiError, ApiRespuesta> call() {
                    return serviceDigimon.deleteDigimon(digimon.getId());
                }
            };

            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(correcto -> {
                            if (correcto.getMessage().equals(Constantes.LLAMADA_CORRECTA_DIGIMON_BORRADO)) {
                                lvDigimon.getItems().removeIf(digimon1 -> digimon1.getId() == digimon.getId());
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
            alert.setContentText(Constantes.SELECCIONA_UN_DIGIMON);
            alert.showAndWait();
        }
    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
