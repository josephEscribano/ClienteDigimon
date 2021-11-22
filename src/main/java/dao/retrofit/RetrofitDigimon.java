package dao.retrofit;

import dao.modelos.ApiRespuesta;
import dao.modelos.Atributos;
import dao.modelos.Digimon;
import dao.modelos.Nivel;
import dao.utils.ConstantesDao;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitDigimon {

    @GET(ConstantesDao.PATH_DIGIMONS)
    Call<List<Digimon>> getDigimons();

    @GET(ConstantesDao.PATH_DIGIMONS_ID)
    Call<Digimon> getDigimon(@Path(ConstantesDao.PATH_ID) int id);

    @GET(ConstantesDao.PATH_DIGIMONS_ATRIBUTOS)
    Call<List<Atributos>> getAtributos();

    @GET(ConstantesDao.PATH_DIGIMONS_NIVELES)
    Call<List<Nivel>> getNiveles();

    @GET(ConstantesDao.PATH_DIGIMONS_SERIEDIGIMONS)
    Call<List<Digimon>> getDigimonsPorSerie(@Query(ConstantesDao.PATH_IDSERIE) int id);

    @POST(ConstantesDao.PATH_DIGIMONS)
    Call<Digimon> addDigimon(@Body Digimon digimon);

    @DELETE(ConstantesDao.PATH_DIGIMONS_ID)
    Call<ApiRespuesta> deleteDigimon(@Path(ConstantesDao.PATH_ID) int id);

    @PUT(ConstantesDao.PATH_DIGIMONS)
    Call<ApiRespuesta> updateDigimon(@Body Digimon digimon);

}
