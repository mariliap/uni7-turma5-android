package com.example.mariliaportela.projetofinal.viewHolder;

import android.view.View;

import com.example.mariliaportela.projetofinal.model.Filme;

public interface ItemFilmeViewHolderContract {

    View.OnClickListener onClick(View view, Filme filme);
}
