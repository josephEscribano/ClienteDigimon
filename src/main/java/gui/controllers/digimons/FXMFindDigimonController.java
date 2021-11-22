package gui.controllers.digimons;

import dao.modelos.ApiError;
import dao.modelos.Digimon;
import gui.controllers.FXMLPrincipalController;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.ServiceDigimon;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMFindDigimonController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;

    @Inject
    private ServiceDigimon serviceDigimon;
    @FXML
    private TextField idBox;
    @FXML
    private ListView<Digimon> listDigimon;

    public void searchById() {
        listDigimon.getItems().clear();
        var tarea = new Task<Either<ApiError, Digimon>>() {
            @Override
            protected Either<ApiError, Digimon> call() {
                return serviceDigimon.findDigimon(Integer.parseInt(idBox.getText()));
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(digimon1 -> listDigimon.getItems().add(digimon1))
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
