package dao;

import dao.modelos.*;
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
public class DaoDIgimons {
    private final Producers producers;

    @Inject
    public DaoDIgimons(Producers producers) {
        this.producers = producers;
    }


    public Either<ApiError, List<Digimon>> getAllDigimnons() {
        Either<ApiError, List<Digimon>> digimonList;
        try {
            Response<List<Digimon>> response = producers.createApi(producers.createRetrofit()).getDigimons().execute();
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

    public Either<ApiError, List<Digimon>> getDigimonPorSerie(int id) {
        Either<ApiError, List<Digimon>> digimonList;
        try {
            Response<List<Digimon>> response = producers.createApi(producers.createRetrofit()).getDigimonsPorSerie(id).execute();
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


    public Either<ApiError, List<Atributos>> getAllAtributos() {
        Either<ApiError, List<Atributos>> digimonList;
        try {
            Response<List<Atributos>> response = producers.createApi(producers.createRetrofit()).getAtributos().execute();
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

    public Either<ApiError, List<Nivel>> getAllNiveles() {
        Either<ApiError, List<Nivel>> digimonList;
        try {
            Response<List<Nivel>> response = producers.createApi(producers.createRetrofit()).getNiveles().execute();
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

    public Either<ApiError, Digimon> addDigimon(Digimon digimon) {
        Either<ApiError, Digimon> resultado;
        try {
            Response<Digimon> response = producers.createApi(producers.createRetrofit()).addDigimon(digimon).execute();
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

    public Either<ApiError, ApiRespuesta> deleteDigimon(int id) {

        Either<ApiError, ApiRespuesta> resultado;
        try {
            Response<ApiRespuesta> response = producers.createApi(producers.createRetrofit()).deleteDigimon(id).execute();

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

    public Either<ApiError, ApiRespuesta> updateDigimon(Digimon digimon) {

        Either<ApiError, ApiRespuesta> resultado;
        try {
            Response<ApiRespuesta> response = producers.createApi(producers.createRetrofit()).updateDigimon(digimon).execute();

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

    public Either<ApiError, Digimon> findDigimon(int id) {
        Either<ApiError, Digimon> resultado;
        try {
            Response<Digimon> response = producers.createApi(producers.createRetrofit()).getDigimon(id).execute();
            if (response.isSuccessful()) {
                resultado = Either.right(response.body());
            } else {
                resultado = Either.left(new ApiError(ConstantesDao.NO_EXISTE_UN_DIGIMON_CON_ESE_ID, LocalDate.now()));
            }
        } catch (IOException e) {

            resultado = Either.left(new ApiError(ConstantesDao.PROBLEMA_SERVIDOR, LocalDate.now()));
            log.error(e.getMessage(), e);
        }

        return resultado;

    }
}
