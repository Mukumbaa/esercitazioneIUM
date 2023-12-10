package com.example.esercitazioneesame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StatisticheFragment extends Fragment {

    private static Persona utente;
    private TextView textViewMsgS,textViewMedia,textViewMediaPonderata,textViewVotoLaurea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistiche, container, false);


        textViewMsgS = view.findViewById(R.id.textViewMsgS);
        textViewMedia = view.findViewById(R.id.textViewMedia);
        textViewMediaPonderata = view.findViewById(R.id.textViewMediaPonderata);
        textViewVotoLaurea = view.findViewById(R.id.textViewVotoLaurea);

        textViewMedia.setText("Hai una media di:\n"+ utente.getMedia());
        textViewMediaPonderata.setText("Hai una media ponderata di:\n"+ utente.getMediaPonderata());
        textViewVotoLaurea.setText("Il tuo voto di laurea previsto sarà di:\n"+ utente.getVotoLaurea());

        return view;
    }
    public static void addUtente(Persona utente) {
        StatisticheFragment.utente = utente;
    }
}