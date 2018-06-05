package com.example.mariliaportela.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mariliaportela.projetofinal.model.FilmeFavorito;
import com.example.mariliaportela.projetofinal.viewHolder.ItemFilmeFavoritoViewHolderContract;
import com.example.mariliaportela.projetofinal.viewHolder.ItemFilmeViewHolder;
import com.example.mariliaportela.projetofinal.viewHolder.ItemFilmeViewHolderContract;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class FilmesFavoritosFragment extends Fragment implements ItemFilmeFavoritoViewHolderContract {


    private View listaFilmesvaforitosWarpperView;
    private RecyclerView listaFilmesFavoritosView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<FilmeFavorito, ItemFilmeViewHolder> adapter;
    private FirebaseDatabase database;
    private DatabaseReference filmesFavoritos;

    private FirebaseAuth firebaseAuth;

    public FilmesFavoritosFragment() {
        // Required empty public constructor
    }

    public static FilmesFavoritosFragment newInstance() {
        FilmesFavoritosFragment fragment = new FilmesFavoritosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database = FirebaseDatabase.getInstance();
        filmesFavoritos = database.getReference("FilmesFavoritos");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("#################1111111111111");
        listaFilmesvaforitosWarpperView = inflater.inflate(R.layout.fragmento_lista_filmes_favoritos, container, false);
        listaFilmesFavoritosView = (RecyclerView) listaFilmesvaforitosWarpperView.findViewById(R.id.listaFilmesFavoritosView);
        listaFilmesFavoritosView.setHasFixedSize(true);

        //final ItemFilmeFavoritoViewHolderAdapter adapter = new ItemFilmeFavoritoViewHolderAdapter(this);
        listaFilmesFavoritosView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(container.getContext());
        listaFilmesFavoritosView.setLayoutManager(layoutManager);

        carregarFilmesFavoritos();
        System.out.println("#################XXXXXXXXXXXXXXXXXX");
        return listaFilmesvaforitosWarpperView;
    }

    private void carregarFilmesFavoritos() {

//        List<FilmeFavorito> filmesFavoritosUsuario = filmesFavoritos.equalTo(firebaseAuth.getCurrentUser().getUid()).getRef().;

//        System.out.println("#####"+);

        ItemFilmeViewHolderContract itemFilmeViewHolderContract;
        adapter = new FirebaseRecyclerAdapter<FilmeFavorito, ItemFilmeViewHolder>(
                FilmeFavorito.class, R.layout.item_filme_layout,
                ItemFilmeViewHolder.class, filmesFavoritos.child(firebaseAuth.getCurrentUser().getUid())) {
            @Override
            protected void populateViewHolder(ItemFilmeViewHolder viewHolder, final FilmeFavorito filme, int position) {

                System.out.println("##################"+filme.toString());
                viewHolder.txtView_nome.setText(filme.getTitle());
//                String urlImagem = CommonInfo.imagesBaseApi + filme.getPoster_path();
                Picasso.with(getActivity()).load(filme.getPoster_path()).into(viewHolder.imgView_imagem);

                System.out.println("##################2222222222222");
                View view = viewHolder.itemView;
                view.setOnClickListener(onClick(view, filme));

            }
        };
        adapter.notifyDataSetChanged();
        listaFilmesFavoritosView.setAdapter(adapter);
    }


    @Override
    public View.OnClickListener onClick(View view, final FilmeFavorito filme) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(), String.format("%s|%s"  , filme.getTitle(), filme.getTitle()),Toast.LENGTH_SHORT).show();
                System.out.println("##################3333333333333333333");
                Intent start = new Intent(getActivity(), DetalhesActivity.class);

                start.putExtra("ID", filme.getIdFilme());
                start.putExtra("IMAGEM", filme.getPoster_path());
                start.putExtra("NOME", filme.getTitle());
                start.putExtra("DESCRICAO", filme.getOverview());
                start.putExtra("DATA_LANCAMENTO", filme.getRelease_date());

                startActivityForResult(start, 222);

            }
        };
    }

}
