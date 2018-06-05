package com.example.mariliaportela.projetofinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment fragmentoFilmes = FilmesFragment.newInstance();
    Fragment fragmentoFilmesFavoritos = FilmesFavoritosFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(HomeActivity.this, "HomeActivity.", Toast.LENGTH_SHORT).show();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragmentoSelecionado =  null;
                switch (item.getItemId()) {
                    case R.id.action_filmes:
                        fragmentoSelecionado = fragmentoFilmes;
                        break;
                    case R.id.action_filmes_favoritos:
                        fragmentoSelecionado = fragmentoFilmesFavoritos;
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentoSelecionado);
                transaction.commit();
                return true;

            }
        });

        setDefaultFragmento();
    }

    private void setDefaultFragmento(){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, FilmesFragment.newInstance());
            transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK){
            boolean result = data.getExtras().getBoolean("isFavorite");
            if (result){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentoFilmesFavoritos);
                transaction.commit();
            }
        }

    }
}
