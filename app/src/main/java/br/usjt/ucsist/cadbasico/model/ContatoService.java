package br.usjt.ucsist.cadbasico.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContatoService {

    @GET("/api/2c010823ad6a4fbfa67922b3f91ec404/contato")
    Call<List<Contato>> getAllContatos();

    @POST("/api/2c010823ad6a4fbfa67922b3f91ec404/contato")
    Call<ResponseBody> salvarContato(
            @Body
                    Contato contato);

    @PUT("/api/2c010823ad6a4fbfa67922b3f91ec404/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body ContatoPut contatoPut);

    @DELETE("/api/2c010823ad6a4fbfa67922b3f91ec404/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}