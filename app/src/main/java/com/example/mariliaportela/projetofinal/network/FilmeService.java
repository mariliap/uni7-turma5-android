package com.example.mariliaportela.projetofinal.network;


import com.example.mariliaportela.projetofinal.model.Filme;
import com.example.mariliaportela.projetofinal.model.Retorno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmeService {

    ///discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc

    // https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=ff2b949bbcc86d83f36dd68f4c219b0f

    @GET("users/{user}/repos")
    Call<List<Filme>> listarFilmes(@Path("user") String user, @Header("api_key") String token);

    //@Field("first_name") String first, @Field("last_name") String last
    @GET("discover/movie?sort_by=popularity.desc")
    Call<Retorno> listarFilmesPopulares(@Query("api_key") String token);
}
