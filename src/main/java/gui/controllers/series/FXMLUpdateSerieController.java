package gui.controllers.series;

import dao.modelos.ApiError;
import dao.modelos.ApiRespuesta;
import dao.modelos.Digimon;
import dao.modelos.Serie;
import dao.utils.ConstantesDao;
import gui.controllers.FXMLPrincipalController;
import gui.utils.Constantes;
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
import java.util.stream.IntStream;

public class FXMLUpdateSerieController implements Initializable {

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

    public void showData(){
        Serie serie = listSeries.getSelectionModel().getSelectedItem();
        tfName.setText(serie.getName());
        dpRelease.setValue(serie.getEstreno());
    }
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

    public void updateSerie(){
        Serie serie = listSeries.getSelectionModel().getSelectedItem();

        if (serie != null){
            serie.setName(tfName.getText());
            serie.setEstreno(dpRelease.getValue());
            int index = -1;

            for (int i = 0; i < listSeries.getItems().size(); i++){
                if (serie.getId() == listSeries.getItems().get(i).getId()){
                    index = i;
                }
            }

            var tarea = new Task<Either<ApiError, ApiRespuesta>>(){
                @Override
                protected Either<ApiError, ApiRespuesta> call() {
                    return serviceSerie.updateSerie(serie);
                }
            };

            int finalIndex = index;
            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(correcto -> {
                            if (correcto.getMessage().equals(Constantes.SERIE_ACTUALIZADA)) {
                                listSeries.getItems().set(finalIndex,serie);
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
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
