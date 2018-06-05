package com.example.mariliaportela.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariliaportela.projetofinal.commons.CommonInfo;
import com.example.mariliaportela.projetofinal.model.Filme;
import com.example.mariliaportela.projetofinal.model.Retorno;
import com.example.mariliaportela.projetofinal.network.FilmeService;
import com.example.mariliaportela.projetofinal.viewHolder.ItemFilmeViewHolderAdapter;
import com.example.mariliaportela.projetofinal.viewHolder.ItemFilmeViewHolderContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FilmesFragment extends Fragment implements ItemFilmeViewHolderContract{


    private View listaFilmesWarpperView;
    private RecyclerView listaFilmesView;
    private RecyclerView.LayoutManager layoutManager;
    private Retrofit retrofit;

    //private final ItemFilmeViewHolderAdapter adapter;

   // https://image.tmdb.org/t/p/w500/


    public FilmesFragment() {
        // Required empty public constructor
    }


    public static FilmesFragment newInstance() {
        FilmesFragment fragment = new FilmesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonInfo.apiToken = "ff2b949bbcc86d83f36dd68f4c219b0f";
        CommonInfo.urlBaseApi = "https://api.themoviedb.org/3/";
        CommonInfo.imagesBaseApi = "https://image.tmdb.org/t/p/w500/";

        retrofit = new Retrofit.Builder()
                .baseUrl(CommonInfo.urlBaseApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        FilmeService service = retrofit.create(FilmeService.class);

        listaFilmesWarpperView = inflater.inflate(R.layout.fragmento_lista_filmes, container, false);
        listaFilmesView = (RecyclerView) listaFilmesWarpperView.findViewById(R.id.listaFilmesView);
        listaFilmesView.setHasFixedSize(true);

        final ItemFilmeViewHolderAdapter adapter = new ItemFilmeViewHolderAdapter(this);
        listaFilmesView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(container.getContext());
        listaFilmesView.setLayoutManager(layoutManager);



        service.listarFilmesPopulares(CommonInfo.apiToken)
                .enqueue(new Callback<Retorno>() {
                    @Override
                    public void onResponse(Call<Retorno> call,
                                           Response<Retorno> response) {

                        for(Filme filme: response.body().getResults()){
                            adapter.adicionar(filme);
                        }
                    }

                    @Override
                    public void onFailure(Call<Retorno> call, Throwable t) {
                        int a = 1;

                    }
                });



        return listaFilmesWarpperView;
    }

    @Override
    public View.OnClickListener onClick(View view, final Filme filme) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent start = new Intent(getActivity(), DetalhesActivity.class);

                start.putExtra("ID", filme.getId());
                start.putExtra("IMAGEM",  CommonInfo.imagesBaseApi+filme.getPoster_path());
                start.putExtra("NOME", filme.getTitle());
                start.putExtra("DESCRICAO", filme.getOverview());
                start.putExtra("DATA_LANCAMENTO", filme.getRelease_date());

                startActivityForResult(start, 123);

            }
        };
    }

}
