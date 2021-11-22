package gui.controllers.digimons;

import dao.modelos.*;
import gui.controllers.FXMLPrincipalController;
import gui.utils.Constantes;
import io.vavr.control.Either;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import service.ServiceDigimon;
import service.ServiceSerie;

import javax.inject.Inject;
import java.util.List;

public class FXMLAddDigimonController {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);


    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceDigimon serviceDigimon;

    @Inject
    private ServiceSerie serviceSerie;

    @FXML
    private ListView<Digimon> listDigimons;
    @FXML
    private TextField tfName;
    @FXML
    private ComboBox<Atributos> cbAtributes;
    @FXML
    private ComboBox<Nivel> cbLevel;
    @FXML
    private DatePicker dpBirth;
    @FXML
    private ListView<Serie> listSeries;


    public void loadDigimon() {

        var tarea = new Task<Either<ApiError, List<Digimon>>>() {
            @Override
            protected Either<ApiError, List<Digimon>> call() {
                return serviceDigimon.getAllDigimons();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(digimons -> listDigimons.getItems().setAll(digimons))
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

    public void loadAtributes() {

        var tarea = new Task<Either<ApiError, List<Atributos>>>() {
            @Override
            protected Either<ApiError, List<Atributos>> call() {
                return serviceDigimon.getAllAtributos();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(atributos -> cbAtributes.getItems().setAll(atributos))
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

    public void loadLevels() {
        var tarea = new Task<Either<ApiError, List<Nivel>>>() {
            @Override
            protected Either<ApiError, List<Nivel>> call() {
                return serviceDigimon.getAllNiveles();
            }
        };

        tarea.setOnSucceeded(workerStateEvent -> {
            tarea.getValue().peek(niveles -> cbLevel.getItems().setAll(niveles))
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

    public void addDigimon() {
        Serie serie = listSeries.getSelectionModel().getSelectedItem();

        if (serie != null) {
            Digimon digimon = new Digimon();
            digimon.setName(tfName.getText());
            digimon.setAtributo(cbAtributes.getValue());
            digimon.setNivel(cbLevel.getValue());
            digimon.setNacimiento(dpBirth.getValue());
            digimon.setIdSerie(serie.getId());
            var tarea = new Task<Either<ApiError, Digimon>>() {
                @Override
                protected Either<ApiError, Digimon> call() {
                    return serviceDigimon.addDigimon(digimon);
                }
            };

            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(digimon1 -> listDigimons.getItems().add(digimon1))
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
            alert.setContentText(Constantes.SELECCIONA_UNA_SERIE_DIGIMON);
            alert.showAndWait();
        }

    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
