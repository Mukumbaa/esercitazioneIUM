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
    private EditText editTextMatricola,editTextPassword;
    public Persona utente;
    private Button buttonLogin,buttonRegistrazione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextMatricola = findViewById(R.id.editTextMatricola);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegistrazione = findViewById(R.id.buttonRegistrazione);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utente = inviaDati();
                String matricola = editTextMatricola.getText().toString();
                String password = editTextPassword.getText().toString();

                boolean emptyFlag = false;

                if(matricola.equals("")){
                    editTextMatricola.setHint("Insierire Matricola");
                    editTextMatricola.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextMatricola.clearFocus();
                    editTextMatricola.setText("");
                    emptyFlag = true;
                }
                if(password.equals("")){
                    editTextPassword.setHint("Inserire Password");
                    editTextPassword.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextPassword.clearFocus();
                    editTextPassword.setText("");
                    emptyFlag = true;
                }

                if (!emptyFlag){
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
                        if(utente.getMatricola() == -1){
                            editTextMatricola.setHint("Matricola errata");
                            editTextMatricola.setHintTextColor(getResources().getColor(R.color.errore));
                            editTextMatricola.clearFocus();
                            editTextMatricola.setText("");
                        }

                        if(utente.getPassword().equals("null")){
                            editTextPassword.setHint("Password errata");
                            editTextPassword.setHintTextColor(getResources().getColor(R.color.errore));
                            editTextPassword.clearFocus();
                            editTextPassword.setText("");
                        }
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

        int matricola = Integer.parseInt(editTextMatricola.getText().toString());
        String password = editTextPassword.getText().toString();


        return Persona.findPersona(matricola,password);
    }



}