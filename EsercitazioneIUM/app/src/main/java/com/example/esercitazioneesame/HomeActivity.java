package com.example.esercitazioneesame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    LibrettoFragment librettoFragment = new LibrettoFragment();
    StatisticheFragment statisticheFragment = new StatisticheFragment();

    private TextView nomeUtente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent precedenteIntent = getIntent();
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        HomeFragment.addUtente(utente);
        LibrettoFragment.addUtente(utente);
        StatisticheFragment.addUtente(utente);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,homeFragment).commit();

        if(precedenteIntent.getStringExtra("fragment").equals("libretto")){
            bottomNavigationView.setSelectedItemId(R.id.libretto);
            getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,librettoFragment).commit();
        }else if(precedenteIntent.getStringExtra("fragment").equals("statistiche")){
            bottomNavigationView.setSelectedItemId(R.id.statistiche);
            getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,statisticheFragment).commit();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,homeFragment).commit();
        }


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.conteiner, homeFragment).commit();
                    return true;
                }
                if(itemId == R.id.libretto){
                    getSupportFragmentManager().beginTransaction().replace(R.id.conteiner, librettoFragment).commit();
                    return true;
                }
                if(itemId == R.id.statistiche){
                    getSupportFragmentManager().beginTransaction().replace(R.id.conteiner, statisticheFragment).commit();
                    return true;
                }

                return false;
            }
        });

    }

}