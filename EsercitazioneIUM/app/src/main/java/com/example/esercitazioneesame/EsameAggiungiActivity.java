package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EsameAggiungiActivity extends AppCompatActivity {

    private ImageButton buttonAggiungiEsame,buttonIndietro;
    private Persona utente;
    private EditText editTextNomeEsame;
    private EditText editTextVoto;
    private EditText editTextCfu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esame_aggiungi);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        utente = (Persona) getIntent().getSerializableExtra("utente");

        buttonAggiungiEsame = findViewById(R.id.buttonAggiungiEsame);
        editTextNomeEsame = findViewById(R.id.editTextNomeEsame);
        editTextVoto = findViewById(R.id.editTextVoto);
        editTextCfu = findViewById(R.id.editTextCfu);
        buttonIndietro = findViewById(R.id.buttonIndietro);

        buttonAggiungiEsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aggiungo l'esame al libretto dell'utente
                utente.addEsame(new Esame(editTextNomeEsame.getText().toString(),Integer.parseInt(editTextVoto.getText().toString()),Integer.parseInt(editTextCfu.getText().toString())));
                savePersona(utente.getMatricola()+".txt", utente.toString());
                //rivado alla pagina del libretto
                Intent intent = new Intent(EsameAggiungiActivity.this, HomeActivity.class);
                //Log.d("AggiungiEsame",utente.getLibretto().get(0).getNomeEsame());
                intent.putExtra("utente",utente);
                intent.putExtra("fragment","libretto");
                startActivity(intent);
                finish();
            }
        });
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EsameAggiungiActivity.this, HomeActivity.class);
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