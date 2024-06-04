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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        //Esame esame = (Esame) precedenteIntent.getSerializableExtra("esame");
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        int posizioneEsame = precedenteIntent.getIntExtra("posizione",0);

        textViewNomeEsame.setText(utente.getLibretto().get(posizioneEsame).getNomeEsame());
        textViewVoto.setText(String.valueOf(utente.getLibretto().get(posizioneEsame).getVoto()));
        textViewCfu.setText(utente.getLibretto().get(posizioneEsame).getCfu() +" CFU");

        buttonEliminaEsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utente.eliminaEsame(posizioneEsame);
                savePersona(utente.getMatricola()+".txt", utente.toString());

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
    public void savePersona(String nomeFile, String sToSave){

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(nomeFile,MODE_PRIVATE);
            fos.write(sToSave.getBytes());
//            Toast.makeText(this,"Saved to "+getFilesDir()+"/"+nomeFile,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}