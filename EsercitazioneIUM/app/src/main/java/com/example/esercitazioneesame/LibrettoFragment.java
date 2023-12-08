package com.example.esercitazioneesame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LibrettoFragment extends Fragment {
    private static Persona utente;
    private TextView textViewMsg;
    private Button aggiungiEsame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_libretto, container, false);

        aggiungiEsame = view.findViewById(R.id.aggiungiEsame);
        textViewMsg = view.findViewById(R.id.textViewMsg);

        if (utente.getLibretto().size() == 0){
            textViewMsg.setText("Nessun voto registrato");
        }

        aggiungiEsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AggiungiEsameActivity.class);
                intent.putExtra("utente",utente);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
    public static void addUtente(Persona utente) {
        LibrettoFragment.utente = utente;
    }

}