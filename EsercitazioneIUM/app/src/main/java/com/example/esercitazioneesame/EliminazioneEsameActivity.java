package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

public class EliminazioneEsameActivity extends AppCompatActivity {
    private ImageButton buttonEliminaEsame,buttonIndietro;

    private TextView textViewNomeEsame,textViewVoto,textViewCfu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminazione_esame);

        buttonEliminaEsame = findViewById(R.id.buttonEliminaEsame);
        buttonIndietro = findViewById(R.id.buttonIndietro);
        textViewNomeEsame = findViewById(R.id.textViewNomeEsame);
        textViewVoto = findViewById(R.id.textViewVoto);
        textViewCfu = findViewById(R.id.textViewCfu);


        Intent precedenteIntent = getIntent();

        Esame esame = (Esame) precedenteIntent.getSerializableExtra("esame");
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        int posizioneEsame = precedenteIntent.getIntExtra("posizione",0);

        textViewNomeEsame.setText(utente.getLibretto().get(posizioneEsame).getNomeEsame());
        textViewVoto.setText(String.valueOf(utente.getLibretto().get(posizioneEsame).getVoto()));
        textViewCfu.setText(String.valueOf(utente.getLibretto().get(posizioneEsame).getCfu())+" CFU");

        buttonEliminaEsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utente.eliminaEsame(posizioneEsame);
//                Log.d("SIUM","qui");
                Intent intent = new Intent(EliminazioneEsameActivity.this, HomeActivity.class);
                intent.putExtra("fragment","libretto");
                intent.putExtra("utente",utente);
                startActivity(intent);
                finish();
            }
        });
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EliminazioneEsameActivity.this, HomeActivity.class);
                intent.putExtra("fragment","libretto");
                intent.putExtra("utente",utente);
                startActivity(intent);
                finish();
            }
        });


    }
}