package com.example.mariliaportela.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariliaportela.projetofinal.commons.CommonInfo;
import com.example.mariliaportela.projetofinal.model.Filme;
import com.example.mariliaportela.projetofinal.model.FilmeFavorito;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    private String id;
    private ImageView filmeImagemView;
    private TextView filmeNomeView;
    private TextView filmeDescricaoView;
    private TextView filmeDataLancamentoView;
    private Button btnFavoritar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference filmesFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        database = FirebaseDatabase.getInstance();
        filmesFavoritos = database.getReference("FilmesFavoritos");
        firebaseAuth = FirebaseAuth.getInstance();


        filmeImagemView = (ImageView) findViewById(R.id.imgFilmePoster);
        filmeNomeView = (TextView) findViewById(R.id.txtFilmeNome);
        filmeDescricaoView = (TextView) findViewById(R.id.txtFilmeDescricao);
        filmeDataLancamentoView = (TextView) findViewById(R.id.txtFilmeDataLancamento);

        this.id = getIntent().getExtras().get("ID").toString();
        final String caminhoImagem =getIntent().getExtras().get("IMAGEM").toString();
        final String nomeFilme = getIntent().getExtras().get("NOME").toString();
        final String descricao = getIntent().getExtras().get("DESCRICAO").toString();
        final String dataLancamento = getIntent().getExtras().get("DATA_LANCAMENTO").toString();


        filmeNomeView.setText(nomeFilme);
        filmeDescricaoView.setText(descricao);
        filmeDataLancamentoView.setText(dataLancamento);
        Picasso.with(DetalhesActivity.this).load(caminhoImagem).into(filmeImagemView);

        btnFavoritar = (Button) findViewById(R.id.btnFavoritar);
        btnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilmeFavorito filmeFavorito = new FilmeFavorito(id, firebaseAuth.getCurrentUser().getUid(),
                        nomeFilme, caminhoImagem, descricao, dataLancamento);

                filmesFavoritos.child(firebaseAuth.getCurrentUser().getUid()).child(filmeFavorito.getIdFilme()).setValue(filmeFavorito);

                showAlertaFilmeFavoritado();
            }
        });
    }

    private void showAlertaFilmeFavoritado() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetalhesActivity.this);
        alertDialog.setTitle("Sucesso");
        alertDialog.setMessage("O filme foi adicionado à sua lista de favoritos");

//        LayoutInflater layoutInflater = this.getLayoutInflater();
//        View favoritoComSucessoView = layoutInflater.inflate(R.layout.sign_up_layout, null);
//        alertDialog.setView(favoritoComSucessoView);

        alertDialog.setNegativeButton("Permanecer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Ir para Lista de Populares",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("isFavorite", true);
                setResult(RESULT_OK, intent);
                finish();

                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
