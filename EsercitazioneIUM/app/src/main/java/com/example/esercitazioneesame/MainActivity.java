package com.example.esercitazioneesame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //public static final String USER_PATH = "com.exemple.esercitazioneesame.utente";
    private EditText editTextMatricola,editTextPassword;
    private TextView registrati;
    public Persona utente;
    private Button /*buttonLogin,*/buttonHidePassword;
    private ImageButton buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextMatricola = findViewById(R.id.editTextMatricola);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonHidePassword = findViewById(R.id.buttonHidePassword);
//        buttonRegistrazione = findViewById(R.id.buttonRegistrazione);
        registrati = findViewById(R.id.registrati);
        SpannableString ss = new SpannableString(registrati.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.accent));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan,20,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registrati.setText(ss);
        registrati.setMovementMethod(LinkMovementMethod.getInstance());
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matricola = editTextMatricola.getText().toString();
                String password = editTextPassword.getText().toString();
                Log.d("SIUM",matricola);
                Log.d("SIUM",password);
                boolean emptyFlag = false;

                if(matricola.equals("")){
                    editTextMatricola.setHint("Insierire Matricola");
                    editTextMatricola.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextMatricola.clearFocus();
                    editTextMatricola.setText("");
                    emptyFlag = true;
                }
                if(!matricola.matches("\\d+")){
                    editTextMatricola.setHint("Matricola ha solo numeri");
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
                        if(utente.getMatricola().equals("null")){
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
        buttonHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SIUM","entro");

                int i = editTextPassword.getInputType();
                Log.d("SIUM",String.valueOf(i));
                if(i == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD))
                {
                    Log.d("SIUM","primo if");
                    editTextPassword.setTypeface(editTextMatricola.getTypeface());
                    buttonHidePassword.setForeground(getDrawable(R.drawable.show));
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    editTextPassword.setTypeface(editTextMatricola.getTypeface());
                    editTextPassword.setSelection(editTextPassword.getText().toString().length());
                }
                if(i == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL)){
                    Log.d("SIUM","secondo if");

                    editTextPassword.setTypeface(editTextMatricola.getTypeface());
                    buttonHidePassword.setForeground(getDrawable(R.drawable.hide));
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    editTextPassword.setTypeface(editTextMatricola.getTypeface());
                    editTextPassword.setSelection(editTextPassword.getText().toString().length());
                }
            }
        });
/*        buttonRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dentro il metodo onClick o in un altro contesto appropriato
                Intent intent = new Intent(MainActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
            }
        });*/
    }
    private Persona inviaDati() {

        String matricola = editTextMatricola.getText().toString();
        String password = editTextPassword.getText().toString();


        return Persona.findPersona(matricola,password);
    }



}