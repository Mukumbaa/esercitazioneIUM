package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

public class EliminazioneEsameActivity extends AppCompatActivity {
    private Button buttonConfermaEliminazione;
    private Button buttonTornaIndietro;

    private TextView riepilogoEsame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminazione_esame);

        buttonConfermaEliminazione = findViewById(R.id.buttonConfermaEliminazione);
        buttonTornaIndietro = findViewById(R.id.buttonTornaIndietro);
        riepilogoEsame = findViewById(R.id.riepilogoEsame);



        Intent precedenteIntent = getIntent();

        Esame esame = (Esame) precedenteIntent.getSerializableExtra("esame");
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        int posizioneEsame = precedenteIntent.getIntExtra("posizione",0);
//        Log.d("utente",utente.getNome()+utente.getCognome());
        riepilogoEsame.setText("Nome Esame: "+esame.getNomeEsame()+"\nVoto: "+esame.getVoto()+"\nCFU: "+esame.getCfu());
        buttonConfermaEliminazione.setOnClickListener(new View.OnClickListener() {
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
        buttonTornaIndietro.setOnClickListener(new View.OnClickListener() {
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