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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //public static final String USER_PATH = "com.exemple.esercitazioneesame.utente";
    private EditText editTextMatricola,editTextPassword;
    private TextView registrati;
    public Persona utente;
    private Button buttonHidePassword;
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


        Persona prova = loadPersona("66137.txt");

        if(!prova.getEsistenza()){
            savePersona("66137.txt","Persona{nome='Gabriele', cognome='Lippolis', dataNascita='06/12/02', matricola='66137', password='password', elencoEsami=[Esame{nome='Algoritmi e strutture dati', voto=25, cfu=9}, Esame{nome='Analisi matematica', voto=27, cfu=9}, Esame{nome='Architettura degli elaboratori', voto=30, cfu=6}, Esame{nome='Fisica e metodo scientifico', voto=25, cfu=6}, Esame{nome='Fondamenti di informatica', voto=25, cfu=6}, Esame{nome='Matematica discreta', voto=28, cfu=9}, Esame{nome='Programmazione 1', voto=26, cfu=12}, Esame{nome='Automi e linguaggi formali', voto=18, cfu=6}, Esame{nome='Colcolo scientifico e metodi numerici', voto=29, cfu=6}, Esame{nome='Dati e modelli', voto=30, cfu=6}, Esame{nome='Elementi di economia e diritto per informatici', voto=21, cfu=6}, Esame{nome='Fondamenti di programmazione web', voto=29, cfu=6}, Esame{nome='Programmazione 2', voto=30, cfu=9}, Esame{nome='Reti di calcolatori', voto=28, cfu=9}, Esame{nome='Sistemi operativi', voto=23, cfu=12}]}");
        }


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



                    Persona p = loadPersona(matricola+".txt");

                    if (p.getEsistenza()){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("utente",p);
                        intent.putExtra("fragment","home");
                        startActivity(intent);
                        finish();
                    }else{
                        editTextMatricola.setHint("Matricola errata");
                        editTextMatricola.setHintTextColor(getResources().getColor(R.color.errore));
                        editTextMatricola.clearFocus();
                        editTextMatricola.setText("");

                        editTextPassword.setHint("Password errata");
                        editTextPassword.setHintTextColor(getResources().getColor(R.color.errore));
                        editTextPassword.clearFocus();
                        editTextPassword.setText("");
                    }

/*                    utente = inviaDati();

                    if (utente.getEsistenza()){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        if (utente.getNome().equals("Gabriele")){
                            utente.setLibretto(new ArrayList<>(Arrays.asList(
                                    new Esame("Algoritmi e strutture dati",25,9),
                                    new Esame("Analisi matematica",27,9),
                                    new Esame("Architettura degli elaboratori",30,6),
                                    new Esame("Fisica e metodo scientifico",25,6),
                                    new Esame("Fondamenti di informatica",25,6),
                                    new Esame("Matematica discreta",28,9),
                                    new Esame("Programmazione 1",26,12),
                                    new Esame("Automi e linguaggi formali",18,6),
                                    new Esame("Colcolo scientifico e metodi numerici",29,6),
                                    new Esame("Dati e modelli",30,6),
                                    new Esame("Elementi di economia e diritto per informatici",21,6),
                                    new Esame("Fondamenti di programmazione web",29,6),
                                    new Esame("Programmazione 2",30,9),
                                    new Esame("Reti di calcolatori",28,9),
                                    new Esame("Sistemi operativi",23,12)


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
                    }*/
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

    public Persona loadPersona(String nomeFile){

        FileInputStream fis = null;
        String sPersona = null;

        try {
            fis = openFileInput(nomeFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String p;

            while ((p = br.readLine()) != null){
                sb.append(p).append("\n");
            }

            sPersona = sb.toString();

//            Toast.makeText(this,sPersona,Toast.LENGTH_LONG).show();

            Persona persona = Persona.fromString(sPersona);
            // Recupera l'elenco degli esami dalla stringa e crea gli oggetti Esame
            String esamiString = sPersona.substring(sPersona.indexOf("[") + 1, sPersona.lastIndexOf("]"));
            if(esamiString.contains(",")){
                String[] esamiArray = esamiString.split(", ");
                for (int i = 0;i<esamiArray.length;i+=3){
                    persona.addEsame(
                            Esame.fromString(esamiArray[i],esamiArray[i+1],esamiArray[i+2])
                    );
                }
            }

            // Toast.makeText(this,persona.toString(),Toast.LENGTH_LONG).show();
            return persona;


        } catch (FileNotFoundException e) {
            Toast.makeText(this,"non esiste",Toast.LENGTH_LONG);

            //throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(this,"non esiste",Toast.LENGTH_LONG);

            //throw new RuntimeException(e);
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return new Persona("","","","","",false);

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