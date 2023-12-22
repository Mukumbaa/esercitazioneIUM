package com.example.esercitazioneesame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.stream.Collectors;



public class HomeFragment extends Fragment {

    private static Persona utente;

    private FrameLayout frameNome,frameCognome,frameDataNascita,frameMatricola,framePassword,frameEliminaAccount;
    private TextView textViewNome,textViewCognome,textViewDataNascita,textViewMatricola,textViewPassword;
    private ImageView imagePassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imagePassword = view.findViewById(R.id.imagePassword);

        frameNome = view.findViewById(R.id.frameNome);
        frameCognome = view.findViewById(R.id.frameCognome);
        frameDataNascita = view.findViewById(R.id.frameDataNascita);
        frameMatricola = view.findViewById(R.id.frameMatricola);
        framePassword = view.findViewById(R.id.framePassword);
        frameEliminaAccount = view.findViewById(R.id.frameEliminaAccount);

        textViewNome = view.findViewById(R.id.textViewNome);
        textViewCognome = view.findViewById(R.id.textViewCognome);
        textViewDataNascita = view.findViewById(R.id.textViewDataNascita);
        textViewMatricola = view.findViewById(R.id.textViewMatricola);
        textViewPassword = view.findViewById(R.id.textViewPassword);

        textViewNome.setText(utente.getNome());
        textViewCognome.setText(utente.getCognome());
        textViewDataNascita.setText(utente.getDataNascita());
        textViewMatricola.setText(utente.getMatricola());
        textViewPassword.setText(
                utente.getPassword()
                .chars()
                .mapToObj(c -> "*")
                .collect(Collectors.joining())
        );


        frameEliminaAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EliminaAccountActivity.class);
                intent.putExtra("utente",utente);
                startActivity(intent);
                getActivity().finish();
            }
        });

        imagePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SIUM","SIUM");

                if(textViewPassword.getText().toString().matches("[*]+")){
                    textViewPassword.setText(utente.getPassword());
                    imagePassword.setImageResource(R.drawable.hide);
                }else{
                    textViewPassword.setText(
                            utente.getPassword()
                                    .chars()
                                    .mapToObj(c -> "*")
                                    .collect(Collectors.joining())
                    );
                    imagePassword.setImageResource(R.drawable.show);
                }

            }
        });


        return view;
    }
    public static void addUtente(Persona utente) {
        HomeFragment.utente = utente;
    }


}