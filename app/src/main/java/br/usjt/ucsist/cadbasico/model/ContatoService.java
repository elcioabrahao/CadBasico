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

    @GET("/api/contatos")
    Call<List<Contato>> getAllContatos();

    @POST("/api/contato")
    Call<ResponseBody> salvarContato(
            @Body
                    Contato contato);

    @PUT("/api/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") Long id,
            @Body Contato contato);

    @DELETE("/api/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") Long id);
}