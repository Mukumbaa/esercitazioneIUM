package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModificaEsameActivity extends AppCompatActivity {

    private EditText editTextNomeEsame,editTextVoto,editTextCfu;
    private ImageButton buttonConfermaModifica,buttonIndietro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_esame);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextNomeEsame = findViewById(R.id.editTextNomeEsame);
        editTextVoto = findViewById(R.id.editTextVoto);
        editTextCfu = findViewById(R.id.editTextCfu);
        buttonConfermaModifica = findViewById(R.id.buttonConfermaModifica);
        buttonIndietro = findViewById(R.id.buttonIndietro);

        Intent precedenteIntent = getIntent();

        Esame esame = (Esame) precedenteIntent.getSerializableExtra("esame");
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        int posizioneEsame = precedenteIntent.getIntExtra("posizione",0);
        Log.d("SIUM",esame.getNomeEsame()+esame.getVoto()+esame.getCfu());

        editTextNomeEsame.setHint(esame.getNomeEsame());
        editTextVoto.setHint(esame.getVoto() + " [Voto]");
        editTextCfu.setHint(esame.getCfu() + " [CFU]");



        buttonConfermaModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeEsame = editTextNomeEsame.getText().toString();
                String voto = editTextVoto.getText().toString();
                String cfu = editTextCfu.getText().toString();

                Esame e = new Esame(
                        nomeEsame.equals("") ? esame.getNomeEsame() : nomeEsame,
                        voto.equals("") ? esame.getVoto() : Integer.parseInt(voto),
                        cfu.equals("") ? esame.getCfu() : Integer.parseInt(cfu)
                );

                utente.modificaEsame(posizioneEsame,e);

                savePersona(utente.getMatricola()+".txt", utente.toString());

                Intent intent = new Intent(ModificaEsameActivity.this, HomeActivity.class);
                intent.putExtra("fragment","libretto");
                intent.putExtra("utente",utente);
                startActivity(intent);
                finish();
            }
        });
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModificaEsameActivity.this, HomeActivity.class);
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