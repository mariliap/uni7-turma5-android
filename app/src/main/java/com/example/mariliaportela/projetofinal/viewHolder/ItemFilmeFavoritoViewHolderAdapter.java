package com.example.mariliaportela.projetofinal.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariliaportela.projetofinal.R;
import com.example.mariliaportela.projetofinal.commons.CommonInfo;
import com.example.mariliaportela.projetofinal.model.Filme;
import com.example.mariliaportela.projetofinal.model.FilmeFavorito;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemFilmeFavoritoViewHolderAdapter extends RecyclerView.Adapter<ItemFilmeViewHolder> {

    private List<FilmeFavorito> filmes = new ArrayList<>();
    private ItemFilmeFavoritoViewHolderContract itemFilmeViewHolderContract;

    public ItemFilmeFavoritoViewHolderAdapter(ItemFilmeFavoritoViewHolderContract itemFilmeViewHolderContract){
        this.itemFilmeViewHolderContract = itemFilmeViewHolderContract;
    }

    @Override
    public ItemFilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View layout = LayoutInflater.from(context).inflate(R.layout.item_filme_layout, parent, false);

        return new ItemFilmeViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ItemFilmeViewHolder holder, int position) {

        FilmeFavorito filme = filmes.get(position);
        holder.txtView_nome.setText(filme.getTitle());

        String urlImagem = CommonInfo.imagesBaseApi + filme.getPoster_path();
        Picasso.with(holder.itemView.getContext()).load(urlImagem).into(holder.imgView_imagem);

        View view = holder.itemView;
        view.setOnClickListener(this.itemFilmeViewHolderContract.onClick(view, filme));

    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public void adicionar(FilmeFavorito filme){
        filmes.add(filme);
        notifyDataSetChanged();
    }

    public FilmeFavorito getRef(int position){
       return filmes.get(position);
    }
}
