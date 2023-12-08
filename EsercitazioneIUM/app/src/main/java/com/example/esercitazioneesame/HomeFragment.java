package com.example.esercitazioneesame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private TextView nomeUtente;
    private TextView cognomeUtente;
    private TextView dataNascitaUtente;
    private TextView idUtente;

    private static Persona utente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nomeUtente = view.findViewById(R.id.nomeUtente);
        cognomeUtente = view.findViewById(R.id.cognomeUtente);
        dataNascitaUtente = view.findViewById(R.id.dataNascitaUtente);
        idUtente = view.findViewById(R.id.idUtente);


        nomeUtente.setText("Nome:  "+utente.getNome());
        cognomeUtente.setText("Cognome:  "+utente.getCognome());
        dataNascitaUtente.setText("Data di nascita:  "+utente.getDataNascita());
        idUtente.setText("ID utente:  "+utente.getID());
        //41041005
        return view;
    }
    public static void addUtente(Persona utente) {
        HomeFragment.utente = utente;
    }
}