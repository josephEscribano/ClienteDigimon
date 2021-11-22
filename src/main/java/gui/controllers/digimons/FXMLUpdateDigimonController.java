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

import javax.inject.Inject;
import java.util.List;

public class FXMLUpdateDigimonController {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FXMLPrincipalController fxmlPrincipalController;
    @Inject
    private ServiceDigimon serviceDigimon;
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

    public void showData() {
        Digimon digimon = listDigimons.getSelectionModel().getSelectedItem();
        tfName.setText(digimon.getName());
        cbAtributes.setValue(digimon.getAtributo());
        cbLevel.setValue(digimon.getNivel());
        dpBirth.setValue(digimon.getNacimiento());
    }

    public void updateDigimon() {
        Digimon digimon = listDigimons.getSelectionModel().getSelectedItem();

        if (digimon != null) {
            digimon.setName(tfName.getText());
            digimon.setAtributo(cbAtributes.getValue());
            digimon.setNivel(cbLevel.getValue());
            digimon.setNacimiento(dpBirth.getValue());
            int index = -1;

            for (int i = 0; i < listDigimons.getItems().size(); i++) {
                if (digimon.getId() == listDigimons.getItems().get(i).getId()) {
                    index = i;
                }
            }

            var tarea = new Task<Either<ApiError, ApiRespuesta>>() {
                @Override
                protected Either<ApiError, ApiRespuesta> call() {
                    return serviceDigimon.updateDigimon(digimon);
                }
            };

            int finalIndex = index;
            tarea.setOnSucceeded(workerStateEvent -> {
                tarea.getValue().peek(correcto -> {
                            if (correcto.getMessage().equals(Constantes.DIGIMON_ACTUALIZADO)) {
                                listDigimons.getItems().set(finalIndex, digimon);
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
        }

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

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        this.fxmlPrincipalController = fxmlPrincipalController;
    }
}
