package service;

import dao.DaoDIgimons;
import dao.modelos.*;
import io.vavr.control.Either;

import javax.inject.Inject;
import java.util.List;

public class ServiceDigimon {
    private final DaoDIgimons dao;

    @Inject
    public ServiceDigimon(DaoDIgimons dao) {
        this.dao = dao;
    }

    public Either<ApiError, List<Digimon>> getAllDigimons() {
        return dao.getAllDigimnons();
    }

    public Either<ApiError, List<Digimon>> getDigimonsPorSerie(int id) {
        return dao.getDigimonPorSerie(id);
    }

    public Either<ApiError, List<Atributos>> getAllAtributos() {
        return dao.getAllAtributos();
    }

    public Either<ApiError, List<Nivel>> getAllNiveles() {
        return dao.getAllNiveles();
    }


    public Either<ApiError, Digimon> addDigimon(Digimon digimon) {
        return dao.addDigimon(digimon);
    }

    public Either<ApiError, ApiRespuesta> deleteDigimon(int id) {
        return dao.deleteDigimon(id);
    }

    public Either<ApiError, ApiRespuesta> updateDigimon(Digimon digimon) {
        return dao.updateDigimon(digimon);
    }

    public Either<ApiError, Digimon> findDigimon(int id) {
        return dao.findDigimon(id);
    }
}
