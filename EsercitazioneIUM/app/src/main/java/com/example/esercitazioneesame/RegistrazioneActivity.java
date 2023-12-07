package com.example.esercitazioneesame;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrazioneActivity extends AppCompatActivity {
    private EditText editTextNome,editTextCognome,editTextPassword,editTextDataNascita;
    public Persona utente;
    private Button buttonRegistrati,buttonTornaAlLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCognome = findViewById(R.id.editTextCognome);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextDataNascita = findViewById(R.id.editTextDataNascita);

        buttonRegistrati = findViewById(R.id.buttonRegistrati);
        buttonTornaAlLogin = findViewById(R.id.buttonTornaAlLogin);

        buttonTornaAlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona utenteNuovo = new Persona(editTextNome.getText().toString(),editTextCognome.getText().toString(),editTextPassword.getText().toString(),editTextDataNascita.getText().toString(),true);

                Intent intent = new Intent(RegistrazioneActivity.this, MessaggioFineRegistrazione.class);

                if (utenteNuovo.checkUtenteEsistente()){
                    intent.putExtra("messaggio","Utente già registrato");
                }else{
                    utenteNuovo.aggiungiUtente();
                    intent.putExtra("messaggio","Registrazione avvenuta con successo");
                }
                intent.putExtra("NomeUtente",utenteNuovo.getNome());
                intent.putExtra("CognomeUtente",utenteNuovo.getCognome());
                startActivity(intent);
                finish();
            }
        });


        // Crea un callback per gestire il pulsante indietro
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //metti un dialog
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }

}