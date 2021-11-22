module clienteDigimon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.yaml.snakeyaml;
    requires lombok;
    requires io.vavr;
    requires retrofit2;
    requires okhttp3;
    requires retrofit2.adapter.rxjava2;
    requires retrofit2.converter.gson;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires javafx.graphics;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires annotations;


    opens gui;
    opens dao;
    opens dao.modelos;
    opens gui.controllers;
    opens gui.controllers.digimons;
    opens gui.controllers.series;
    opens service;
    opens config;



    exports gui;
    exports gui.controllers;
    exports gui.controllers.digimons;
    exports gui.controllers.series;
    exports dao;
    exports dao.modelos;
    exports service;
    exports config;
    exports gui.utils;
    opens gui.utils;
    exports dao.utils;
    opens dao.utils;
    exports dao.retrofit;
    opens dao.retrofit;


}