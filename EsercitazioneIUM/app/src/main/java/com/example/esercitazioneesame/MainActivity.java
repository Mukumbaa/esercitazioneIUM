package com.example.esercitazioneesame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //public static final String USER_PATH = "com.exemple.esercitazioneesame.utente";
    private EditText editTextNome,editTextCognome,editTextPassword;
    public Persona utente;
    private Button buttonLogin,buttonRegistrazione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCognome = findViewById(R.id.editTextCognome);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegistrazione = findViewById(R.id.buttonRegistrazione);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utente = inviaDati();
                if (utente.getEsistenza()){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    //intent.putExtra("NomeUtente",utente.getNome());
                    if (utente.getNome().equals("Gabriele")){
                        utente.setLibretto(new ArrayList<>(Arrays.asList(
                                new Esame(30,"Analisi 1",9),
                                new Esame(26,"Programmazione 1",12),
                                new Esame(18,"ALF",6),
                                new Esame(21,"EDI",6),
                                new Esame(30,"Calcolo e metodo scientifico",9),
                                new Esame(30,"nome lunghissimissimissimo incredibile gigante",9),
                                new Esame(30,"Analisi 1",9),
                                new Esame(26,"Programmazione 1",12),
                                new Esame(18,"ALF",6),
                                new Esame(21,"EDI",6),
                                new Esame(30,"Calcolo e metodo scientifico",9)

                        )));
                    }
                    intent.putExtra("utente",utente);
                    intent.putExtra("fragment","home");
                    startActivity(intent);
                    finish();
                }else{
                    if(utente.getNome().equals("null")){
                        editTextNome.setHint("Nome errato");
                        editTextNome.setHintTextColor(getResources().getColor(R.color.errore));
                        editTextNome.clearFocus();
                        editTextNome.setText("");
                    }
                    if(utente.getCognome().equals("null")){
                        editTextCognome.setHint("Cognome errato");
                        editTextCognome.setHintTextColor(getResources().getColor(R.color.errore));
                        editTextCognome.clearFocus();
                        editTextCognome.setText("");
                    }
                    if(utente.getPassword().equals("null")){
                        editTextPassword.setHint("Password errata");
                        editTextPassword.setHintTextColor(getResources().getColor(R.color.errore));
                        editTextPassword.clearFocus();
                        editTextPassword.setText("");
                    }
                }

            }
        });
        buttonRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dentro il metodo onClick o in un altro contesto appropriato
                Intent intent = new Intent(MainActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
            }
        });
    }
    private Persona inviaDati() {
        String nome = editTextNome.getText().toString();
        String cognome = editTextCognome.getText().toString();
        String password = editTextPassword.getText().toString();

        return Persona.findPersona(nome,cognome,password);
    }



}