package service;

import dao.DaoSeries;
import dao.modelos.ApiError;
import dao.modelos.ApiRespuesta;
import dao.modelos.Serie;
import io.vavr.control.Either;

import javax.inject.Inject;
import java.util.List;

public class ServiceSerie {
    private final DaoSeries dao;

    @Inject
    public ServiceSerie(DaoSeries dao) {
        this.dao = dao;
    }

    public Either<ApiError, List<Serie>> getSeries() {
        return dao.getAllSeries();
    }

    public Either<ApiError, Serie> addSerie(Serie serie) {
        return dao.addSerie(serie);
    }

    public Either<ApiError, ApiRespuesta> deleteSerie(int id) {
        return dao.deleteSerie(id);
    }

    public Either<ApiError, ApiRespuesta> updateSerie(Serie serie) {
        return dao.updateSerie(serie);
    }

    public Either<ApiError, Serie> findSerie(int id) {
        return dao.findSerie(id);
    }
}
