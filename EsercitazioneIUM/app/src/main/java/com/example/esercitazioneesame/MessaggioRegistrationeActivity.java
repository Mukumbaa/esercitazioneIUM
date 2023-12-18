package com.example.esercitazioneesame;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MessaggioRegistrationeActivity extends AppCompatActivity {
    private ImageButton buttonTornaAlLoginConfermaRegistrazione;
    private TextView messaggio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaggio_registratione);



        messaggio = findViewById(R.id.messaggio);
        Intent precedenteIntent = getIntent();

        String messaggioUtente = precedenteIntent.getStringExtra("messaggio");
        String NomeUtente = precedenteIntent.getStringExtra("NomeUtente");
        String CognomeUtente = precedenteIntent.getStringExtra("CognomeUtente");
        if (messaggioUtente.equals("Utente già registrato")){
            messaggio.setText(messaggioUtente);
        }else{
            String text = messaggioUtente+"\n"+"Benvenuto "+NomeUtente+" "+CognomeUtente;
            messaggio.setText(text);
        }


        buttonTornaAlLoginConfermaRegistrazione = findViewById(R.id.buttonTornaAlLoginConfermaRegistrazione);

        buttonTornaAlLoginConfermaRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessaggioRegistrationeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Chiudi la terza Activity
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //metti un dialog
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);







    }
}