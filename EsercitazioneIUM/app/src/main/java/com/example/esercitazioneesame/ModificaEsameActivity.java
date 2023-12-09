package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ModificaEsameActivity extends AppCompatActivity {

    private EditText editTextNomeEsameM,editTextVotoM,editTextCfuM;
    private Button buttonConfermaModifica,buttonTornaIndietroM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_esame);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextNomeEsameM = findViewById(R.id.editTextNomeEsameM);
        editTextVotoM = findViewById(R.id.editTextVotoM);
        editTextCfuM = findViewById(R.id.editTextCfuM);
        buttonConfermaModifica = findViewById(R.id.buttonConfermaModifica);
        buttonTornaIndietroM = findViewById(R.id.buttonTornaIndietroM);

        Intent precedenteIntent = getIntent();

        Esame esame = (Esame) precedenteIntent.getSerializableExtra("esame");
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");
        int posizioneEsame = precedenteIntent.getIntExtra("posizione",0);
        Log.d("SIUM",esame.getNomeEsame()+esame.getVoto()+esame.getCfu());

        editTextNomeEsameM.setHint(esame.getNomeEsame());
        editTextVotoM.setHint(String.valueOf(esame.getVoto()));
        editTextCfuM.setHint(String.valueOf(esame.getCfu()));



        buttonConfermaModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Esame e = new Esame(Integer.parseInt(editTextVotoM.getText().toString()),editTextNomeEsameM.getText().toString(),Integer.parseInt(editTextCfuM.getText().toString()));
                utente.modificaEsame(e,posizioneEsame);
                Intent intent = new Intent(ModificaEsameActivity.this, HomeActivity.class);
                intent.putExtra("fragment","libretto");
                intent.putExtra("utente",utente);
                startActivity(intent);
                finish();
            }
        });
        buttonTornaIndietroM.setOnClickListener(new View.OnClickListener() {
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
}