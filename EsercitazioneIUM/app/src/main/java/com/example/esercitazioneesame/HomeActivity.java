package com.example.esercitazioneesame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {


    private TextView sium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent precedenteIntent = getIntent();
        Persona utente = (Persona) precedenteIntent.getSerializableExtra("utente");

        sium = findViewById(R.id.sium);
        sium.setText(utente.getNome());

    }

}