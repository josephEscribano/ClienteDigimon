package dao.retrofit;

import dao.modelos.ApiRespuesta;
import dao.modelos.Serie;
import dao.utils.ConstantesDao;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitSerie {

    @GET(ConstantesDao.PATH_SERIES)
    Call<List<Serie>> getSeries();

    @GET(ConstantesDao.PATH_SERIES_ID)
    Call<Serie> getSerie(@Path(ConstantesDao.PATH_ID) int id);

    @POST(ConstantesDao.PATH_SERIES)
    Call<Serie> addSerie(@Body Serie serie);

    @DELETE(ConstantesDao.PATH_SERIES_ID)
    Call<ApiRespuesta> deleteSerie(@Path(ConstantesDao.PATH_ID) int id);

    @PUT(ConstantesDao.PATH_SERIES)
    Call<ApiRespuesta> updateSerie(@Body Serie serie);
}
