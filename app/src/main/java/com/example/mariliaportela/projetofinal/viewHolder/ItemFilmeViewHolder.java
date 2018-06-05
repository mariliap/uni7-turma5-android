package com.example.mariliaportela.projetofinal.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariliaportela.projetofinal.R;
import com.example.mariliaportela.projetofinal.contrato.ItemClickListener;

public class ItemFilmeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtView_nome;
    public ImageView imgView_imagem;

    private ItemClickListener itemClickListener;

    public ItemFilmeViewHolder(View itemView) {
        super(itemView);
        imgView_imagem = (ImageView)  itemView.findViewById(R.id.item_filme_imagem);
        txtView_nome = (TextView)  itemView.findViewById(R.id.item_filme_nome);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
