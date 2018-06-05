package com.example.mariliaportela.projetofinal.viewHolder;

import android.view.View;

import com.example.mariliaportela.projetofinal.model.Filme;
import com.example.mariliaportela.projetofinal.model.FilmeFavorito;

public interface ItemFilmeFavoritoViewHolderContract {

    View.OnClickListener onClick(View view, FilmeFavorito filme);
}
