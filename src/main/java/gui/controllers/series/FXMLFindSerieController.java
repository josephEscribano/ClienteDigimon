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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.ServiceSerie;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLFindSerieController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceSerie serviceSerie;
    @FXML
    private ListView<Serie> listSeries;
    @FXML
    private TextField idBox;


    public void searchById() {
        listSeries.getItems().clear();
        var tarea = new Task<Either<ApiError, Serie>>() {
            @Override
            protected Either<ApiError, Serie> call() {
                return serviceSerie.findSerie(Integer.parseInt(idBox.getText()));
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

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
