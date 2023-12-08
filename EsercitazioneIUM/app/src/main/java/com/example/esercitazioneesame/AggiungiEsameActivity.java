package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AggiungiEsameActivity extends AppCompatActivity {

    private Button buttonAggiungiEsame;
    private Persona utente;
    private EditText editTextNomeEsame;
    private EditText editTextVoto;
    private EditText editTextCfu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_esame);

        utente = (Persona) getIntent().getSerializableExtra("utente");

        buttonAggiungiEsame = findViewById(R.id.buttonAggiungiEsame);
        editTextNomeEsame = findViewById(R.id.editTextNomeEsame);
        editTextVoto = findViewById(R.id.editTextVoto);
        editTextCfu = findViewById(R.id.editTextCfu);


        buttonAggiungiEsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aggiungo l'esame al libretto dell'utente
                utente.addEsame(new Esame(Integer.parseInt(editTextVoto.getText().toString()),editTextNomeEsame.getText().toString(),Integer.parseInt(editTextCfu.getText().toString())));
                //rivado alla pagina del libretto
                Intent intent = new Intent(AggiungiEsameActivity.this, HomeActivity.class);
                //Log.d("AggiungiEsame",utente.getLibretto().get(0).getNomeEsame());
                intent.putExtra("utente",utente);
                intent.putExtra("fragment","libretto");
                startActivity(intent);
                finish();
            }
        });

    }
}