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


        if(utente.getLibretto().size() == 0){
            textViewMsgS.setText("Nessun voto registrato");
            textViewMedia.setVisibility(View.GONE);
            textViewMediaPonderata.setVisibility(View.GONE);
            textViewVotoLaurea.setVisibility(View.GONE);
        }else{
            textViewMsgS.setVisibility(View.GONE);
            textViewMedia.setText("Media:\n"+ utente.getMedia());
            textViewMediaPonderata.setText("Media ponderata:\n"+ utente.getMediaPonderata());
            textViewVotoLaurea.setText("Voto di laurea previsto:\n"+ utente.getVotoLaurea());
        }



//        textViewMedia.setText("Media:\n"+ utente.getMedia());
//        textViewMediaPonderata.setText("Media ponderata:\n"+ utente.getMediaPonderata());
//        textViewVotoLaurea.setText("Voto di laurea previsto:\n"+ utente.getVotoLaurea());

        return view;
    }
    public static void addUtente(Persona utente) {
        StatisticheFragment.utente = utente;
    }
}