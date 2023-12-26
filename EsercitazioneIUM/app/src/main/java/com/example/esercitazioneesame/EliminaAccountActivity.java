package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class EliminaAccountActivity extends AppCompatActivity {

    private TextView textViewNome, textViewMatricola, textViewDataNascita, textViewNumEsami;
    private ImageButton buttonEliminaAccount, buttonIndietro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina_account);

        Intent intentPrecedente = getIntent();

        textViewNome = findViewById(R.id.textViewNome);
        textViewMatricola = findViewById(R.id.textViewMatricola);
        textViewDataNascita = findViewById(R.id.textViewDataNascita);
        textViewNumEsami = findViewById(R.id.textViewNumEsami);
        buttonEliminaAccount = findViewById(R.id.buttonEliminaAccount);
        buttonIndietro = findViewById(R.id.buttonIndietro);

        Persona utente = (Persona) intentPrecedente.getSerializableExtra("utente");

        textViewNome.setText(utente.getNome()+" "+utente.getCognome());
        textViewMatricola.setText(utente.getMatricola());
        textViewDataNascita.setText(utente.getDataNascita());
        textViewNumEsami.setText(utente.getLibretto().size()+" esami conseguiti");


        buttonEliminaAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Persona.eliminaUtente(utente);

                File file = new File(getFilesDir(),utente.getMatricola()+".txt");

                file.delete();

                Intent intent = new Intent(EliminaAccountActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(EliminaAccountActivity.this, "Account eliminato", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EliminaAccountActivity.this, HomeActivity.class);
                intent.putExtra("fragment","home");
                intent.putExtra("utente",utente);
                startActivity(intent);
                finish();
            }
        });
    }
}