package dao;

import dao.modelos.ApiError;
import dao.modelos.ApiRespuesta;
import dao.modelos.Serie;
import dao.utils.ConstantesDao;
import gui.Producers;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class DaoSeries {

    private final Producers producers;

    @Inject
    public DaoSeries(Producers producers) {
        this.producers = producers;
    }

    public Either<ApiError, List<Serie>> getAllSeries() {
        Either<ApiError, List<Serie>> digimonList;
        try {
            Response<List<Serie>> response = producers.createApiSerie(producers.createRetrofit()).getSeries().execute();
            if (response.isSuccessful()) {
                digimonList = Either.right(response.body());
            } else {

                ApiError apiError = producers.getGson().fromJson(response.errorBody().string(), ApiError.class);
                digimonList = Either.left(apiError);
            }
        } catch (IOException e) {
            digimonList = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return digimonList;
    }

    public Either<ApiError, Serie> addSerie(Serie serie) {
        Either<ApiError, Serie> resultado;
        try {
            Response<Serie> response = producers.createApiSerie(producers.createRetrofit()).addSerie(serie).execute();
            if (response.isSuccessful()) {
                resultado = Either.right(response.body());
            } else {
                resultado = Either.left(new ApiError(ConstantesDao.NO_SE_HA_PODIDO_AÑADIR_EL_DIGIMON, LocalDate.now()));
            }
        } catch (IOException e) {

            resultado = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return resultado;

    }

    public Either<ApiError, ApiRespuesta> deleteSerie(int id) {

        Either<ApiError, ApiRespuesta> resultado;
        try {
            Response<ApiRespuesta> response = producers.createApiSerie(producers.createRetrofit()).deleteSerie(id).execute();

            if (response.isSuccessful()) {

                resultado = Either.right(response.body());
            } else {
                ApiError apiError = producers.getGson().fromJson(response.errorBody().string(), ApiError.class);
                resultado = Either.left(apiError);
            }
        } catch (IOException e) {
            resultado = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return resultado;
    }

    public Either<ApiError, ApiRespuesta> updateSerie(Serie serie) {

        Either<ApiError, ApiRespuesta> resultado;
        try {
            Response<ApiRespuesta> response = producers.createApiSerie(producers.createRetrofit()).updateSerie(serie).execute();

            if (response.isSuccessful()) {

                resultado = Either.right(response.body());
            } else {
                ApiError apiError = producers.getGson().fromJson(response.errorBody().string(), ApiError.class);
                resultado = Either.left(apiError);
            }
        } catch (IOException e) {
            resultado = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return resultado;
    }

    public Either<ApiError, Serie> findSerie(int id) {
        Either<ApiError, Serie> resultado;
        try {
            Response<Serie> response = producers.createApiSerie(producers.createRetrofit()).getSerie(id).execute();
            if (response.isSuccessful()) {
                resultado = Either.right(response.body());
            } else {
                resultado = Either.left(new ApiError(ConstantesDao.NO_EXISTE_UNA_SERIE_CON_ESE_ID, LocalDate.now()));
            }
        } catch (IOException e) {

            resultado = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return resultado;

    }
}
