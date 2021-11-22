package gui.controllers;

import gui.controllers.digimons.*;
import gui.controllers.series.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLPrincipalController implements Initializable {

    private final FXMLLoader fxmlLoaderaddDigimon;
    private final FXMLLoader fxmlLoaderDeleteDigimon;
    private final FXMLLoader fxmlLoaderListDigimon;
    private final FXMLLoader fxmlLoaderUpdateDigimon;
    private final FXMLLoader fxmlLoaderFindDigimon;
    private final FXMLLoader fxmlLoaderaddSerie;
    private final FXMLLoader fxmlLoaderDeleteSerie;
    private final FXMLLoader fxmlLoaderListSerie;
    private final FXMLLoader fxmlLoaderUpdateSerie;
    private final FXMLLoader fxmlLoaderFindSerie;
    @FXML
    private BorderPane fxRoot;
    private AnchorPane addDigimon;
    private FXMLAddDigimonController fxmlAddDigimonController;
    private AnchorPane deleteDigimon;
    private FXMLDeleteDigimonController fxmlDeleteDigimonController;
    private AnchorPane listDigimon;
    private FXMLListDigimonController fxmlListDigimonController;
    private AnchorPane updateDigimon;
    private FXMLUpdateDigimonController fxmlUpdateDigimonController;
    private AnchorPane findDigimon;
    private FXMFindDigimonController fxmlFindDigimonController;
    private AnchorPane addSeries;
    private FXMLAddSerieController fxmlAddSerieController;
    private AnchorPane deleteSeries;
    private FXMLDeleteSerieController fxmlDeleteSerieController;
    private AnchorPane listSeries;
    private FXMLListSerieController fxmlListSerieController;
    private AnchorPane updateSeries;
    private FXMLUpdateSerieController fxmlUpdateSerieController;
    private AnchorPane findSerie;
    private FXMLFindSerieController fxmlFindSerieController;


    @Inject
    public FXMLPrincipalController(FXMLLoader fxmlLoaderaddDigimon,
                                   FXMLLoader fxmlLoaderDeleteDigimon,
                                   FXMLLoader fxmlLoaderListDigimon,
                                   FXMLLoader fxmlLoaderUpdateDigimon,
                                   FXMLLoader fxmlLoaderFindDigimon, FXMLLoader fxmlLoaderaddSerie,
                                   FXMLLoader fxmlLoaderDeleteSerie,
                                   FXMLLoader fxmlLoaderListSerie,
                                   FXMLLoader fxmlLoaderUpdateSerie, FXMLLoader fxmlLoaderFindSerie) {
        this.fxmlLoaderaddDigimon = fxmlLoaderaddDigimon;

        this.fxmlLoaderDeleteDigimon = fxmlLoaderDeleteDigimon;
        this.fxmlLoaderListDigimon = fxmlLoaderListDigimon;
        this.fxmlLoaderUpdateDigimon = fxmlLoaderUpdateDigimon;
        this.fxmlLoaderFindDigimon = fxmlLoaderFindDigimon;
        this.fxmlLoaderaddSerie = fxmlLoaderaddSerie;
        this.fxmlLoaderDeleteSerie = fxmlLoaderDeleteSerie;
        this.fxmlLoaderListSerie = fxmlLoaderListSerie;
        this.fxmlLoaderUpdateSerie = fxmlLoaderUpdateSerie;
        this.fxmlLoaderFindSerie = fxmlLoaderFindSerie;
    }


    public BorderPane getFxRoot() {
        return fxRoot;
    }

    public void preloadAddDigimon() {
        try {
            if (addDigimon == null) {
                addDigimon = fxmlLoaderaddDigimon.load(getClass().getResourceAsStream("/fxml/digimons/FXMLAddDigimon.fxml"));
                fxmlAddDigimonController = fxmlLoaderaddDigimon.getController();
                fxmlAddDigimonController.setPrincipal(this);
            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeleteDigimon() {
        try {
            if (deleteDigimon == null) {
                deleteDigimon = fxmlLoaderDeleteDigimon.load(getClass().getResourceAsStream("/fxml/digimons/FXMLDeleteDigimon.fxml"));
                fxmlDeleteDigimonController = fxmlLoaderDeleteDigimon.getController();
                fxmlDeleteDigimonController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadListDigimon() {
        try {
            if (listDigimon == null) {
                listDigimon = fxmlLoaderListDigimon.load(getClass().getResourceAsStream("/fxml/digimons/FXMLListDigimon.fxml"));
                fxmlListDigimonController = fxmlLoaderListDigimon.getController();
                fxmlListDigimonController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadUpdateDigimon() {
        try {
            if (updateDigimon == null) {
                updateDigimon = fxmlLoaderUpdateDigimon.load(getClass().getResourceAsStream("/fxml/digimons/FXMLUpdateDigimon.fxml"));
                fxmlUpdateDigimonController = fxmlLoaderUpdateDigimon.getController();
                fxmlUpdateDigimonController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadFindDigimon() {
        try {
            if (findDigimon == null) {
                findDigimon = fxmlLoaderFindDigimon.load(getClass().getResourceAsStream("/fxml/digimons/FXMLFindDigimon.fxml"));
                fxmlFindDigimonController = fxmlLoaderFindDigimon.getController();
                fxmlFindDigimonController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void preloadAddSerie() {
        try {
            if (addSeries == null) {
                addSeries = fxmlLoaderaddSerie.load(getClass().getResourceAsStream("/fxml/series/FXMLAddSerie.fxml"));
                fxmlAddSerieController = fxmlLoaderaddSerie.getController();
                fxmlAddSerieController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeleteSerie() {
        try {
            if (deleteSeries == null) {
                deleteSeries = fxmlLoaderDeleteSerie.load(getClass().getResourceAsStream("/fxml/series/FXMLDeleteSerie.fxml"));
                fxmlDeleteSerieController = fxmlLoaderDeleteSerie.getController();
                fxmlDeleteSerieController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadListSerie() {
        try {
            if (listSeries == null) {
                listSeries = fxmlLoaderListSerie.load(getClass().getResourceAsStream("/fxml/series/FXMLListSerie.fxml"));
                fxmlListSerieController = fxmlLoaderListSerie.getController();
                fxmlListSerieController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadUpdateSerie() {
        try {
            if (updateSeries == null) {
                updateSeries = fxmlLoaderUpdateSerie.load(getClass().getResourceAsStream("/fxml/series/FXMLUpdateSerie.fxml"));
                fxmlUpdateSerieController = fxmlLoaderUpdateSerie.getController();
                fxmlUpdateSerieController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadFindSerie() {
        try {
            if (findSerie == null) {
                findSerie = fxmlLoaderFindSerie.load(getClass().getResourceAsStream("/fxml/series/FXMLFindSerie.fxml"));
                fxmlFindSerieController = fxmlLoaderFindSerie.getController();
                fxmlFindSerieController.setPrincipal(this);
            }


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chargeAddDigimon() {
        fxmlAddDigimonController.loadDigimon();
        fxmlAddDigimonController.loadSeries();
        fxmlAddDigimonController.loadAtributes();
        fxmlAddDigimonController.loadLevels();
        fxRoot.setCenter(addDigimon);

    }

    public void chargeDeleteDigimon() {
        fxmlDeleteDigimonController.loadDigimon();
        fxRoot.setCenter(deleteDigimon);
    }

    public void chargeUpdateDigimon() {
        fxmlUpdateDigimonController.loadDigimon();
        fxmlUpdateDigimonController.loadAtributes();
        fxmlUpdateDigimonController.loadLevels();
        fxRoot.setCenter(updateDigimon);
    }

    public void chargeListDigimon() {
        fxmlListDigimonController.loadDigimon();
        fxRoot.setCenter(listDigimon);
    }
    public void chargeFindDigimon() {
        fxRoot.setCenter(findDigimon);
    }

    public void chargeAddSerie() {
        fxmlAddSerieController.loadSeries();
        fxRoot.setCenter(addSeries);
    }

    public void chargeDeleteSerie() {
        fxmlDeleteSerieController.loadSeries();
        fxRoot.setCenter(deleteSeries);
    }

    public void chargeListSerie() {
        fxmlListSerieController.laadSeries();
        fxRoot.setCenter(listSeries);
    }

    public void chargeUpdateSerie() {
        fxmlUpdateSerieController.loadSeries();
        fxRoot.setCenter(updateSeries);
    }
    public void chargeFindSerie() {
        fxRoot.setCenter(findSerie);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preloadAddDigimon();
        preloadDeleteDigimon();
        preloadListDigimon();
        preloadUpdateDigimon();
        preloadAddSerie();
        preloadDeleteSerie();
        preloadListSerie();
        preloadListSerie();
        preloadUpdateSerie();
        preloadFindDigimon();
        preloadFindSerie();
    }
}
