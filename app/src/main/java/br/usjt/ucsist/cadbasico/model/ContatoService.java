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

    //https://crudcrud.com/api/44cdb2bab6214a2583ce2d74464a6880

    @GET("/api/44cdb2bab6214a2583ce2d74464a6880/contato")
    Call<List<Contato>> getAllContatos();

    @POST("/api/44cdb2bab6214a2583ce2d74464a6880/contato")
    Call<ResponseBody> salvarContato(
            @Body
                    Contato contato);

    @PUT("/api/44cdb2bab6214a2583ce2d74464a6880/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body ContatoPut contatoPut);

    @DELETE("/api/44cdb2bab6214a2583ce2d74464a6880/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}