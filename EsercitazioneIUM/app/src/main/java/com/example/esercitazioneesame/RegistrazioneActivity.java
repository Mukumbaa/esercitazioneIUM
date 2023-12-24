package com.example.esercitazioneesame;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class RegistrazioneActivity extends AppCompatActivity {
    private EditText editTextNome,editTextCognome,editTextMatricola,editTextPassword,editTextDataNascita;
    public Persona utente;
    private TextView accedi;
    private Calendar myCalendar= Calendar.getInstance();

    private Button/*buttonRegistrati,*/buttonHidePassword;
    private ImageButton buttonRegistrati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCognome = findViewById(R.id.editTextCognome);
        editTextMatricola = findViewById(R.id.editTextMatricola);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextDataNascita = findViewById(R.id.editTextDataNascita);
        buttonHidePassword = findViewById(R.id.buttonHidePassword);
        accedi = findViewById(R.id.accedi);

        buttonRegistrati = findViewById(R.id.buttonRegistrati);
        SpannableString ss = new SpannableString(accedi.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(RegistrazioneActivity.this, MainActivity.class);
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
        ss.setSpan(clickableSpan,20,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        accedi.setText(ss);
        accedi.setMovementMethod(LinkMovementMethod.getInstance());


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        DatePickerDialog d = new DatePickerDialog(RegistrazioneActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));

        editTextDataNascita.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!d.isShowing()) {
                    // Chiudi il dialogo precedente se è ancora aperto
                    d.dismiss();
                    // Mostra un nuovo DatePickerDialog
                    d.show();
                }else {
                    d.show();
                }
                return false;
            }
        });
/*        editTextDataNascita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistrazioneActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/
        buttonRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editTextNome.getText().toString();
                String cognome = editTextCognome.getText().toString();
                String dataNascita = editTextDataNascita.getText().toString();
                String matricola = editTextMatricola.getText().toString();
                String password = editTextPassword.getText().toString();

                boolean emptyFlag = false;

                if(nome.equals("")){
                    editTextNome.setHint("Insierire Nome");
                    editTextNome.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextNome.clearFocus();
                    editTextNome.setText("");
                    emptyFlag = true;
                }
                if(cognome.equals("")){
                    editTextCognome.setHint("Inserire Cognome");
                    editTextCognome.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextCognome.clearFocus();
                    editTextCognome.setText("");
                    emptyFlag = true;
                }
                if(password.equals("")){
                    editTextDataNascita.setHint("Inserire Data di Nascita");
                    editTextDataNascita.setHintTextColor(getResources().getColor(R.color.errore));
                    editTextDataNascita.clearFocus();
                    editTextDataNascita.setText("");
                    emptyFlag = true;
                }
                if(matricola.equals("")){
                    editTextMatricola.setHint("Inserire Matricola");
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

                if(!emptyFlag){
                    Persona utenteNuovo = new Persona(nome,cognome,matricola,password,dataNascita,true);

                    Intent intent = new Intent(RegistrazioneActivity.this, MessaggioRegistrationeActivity.class);

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
        // Crea un callback per gestire il pulsante indietro
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //metti un dialog
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }
    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.ITALY);
        editTextDataNascita.setText(dateFormat.format(myCalendar.getTime()));
    }

}