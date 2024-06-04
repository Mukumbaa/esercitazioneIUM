package com.example.esercitazioneesame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class StatisticheFragment extends Fragment {

    private static Persona utente;
    private TextView textViewMsgS,textViewMedia,textViewMediaPonderata,textViewVotoLaurea;
    private FrameLayout frameMedia, frameMediaPonderata, frameVotoLaurea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistiche, container, false);

        textViewMsgS = view.findViewById(R.id.textViewMsgS);
        textViewMedia = view.findViewById(R.id.textViewMedia);
        textViewMediaPonderata = view.findViewById(R.id.textViewMediaPonderata);
        textViewVotoLaurea = view.findViewById(R.id.textViewVotoLaurea);

        frameMedia = view.findViewById(R.id.frameMedia);
        frameMediaPonderata = view.findViewById(R.id.frameMediaPonderata);
        frameVotoLaurea = view.findViewById(R.id.frameVotoLaurea);

        // Ottieni la larghezza dello schermo
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getContext() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;

            // Calcola la larghezza desiderata
            int marginInDp = 60;
            float marginInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
            int targetWidth = (screenWidth - (int) marginInPixels)/2 - 15;

            // Ottieni il FrameLayout e imposta la larghezza
            ViewGroup.LayoutParams layoutParams1 = frameMedia.getLayoutParams();
            ViewGroup.LayoutParams layoutParams2 = frameMediaPonderata.getLayoutParams();

            layoutParams1.width = targetWidth;
            layoutParams2.width = targetWidth;

            frameMedia.setLayoutParams(layoutParams1);
            frameMediaPonderata.setLayoutParams(layoutParams2);
        }

        if(utente.getLibretto().size() == 0){
            textViewMsgS.setText("Nessun voto registrato");
            textViewMedia.setVisibility(View.GONE);
            textViewMediaPonderata.setVisibility(View.GONE);
            textViewVotoLaurea.setVisibility(View.GONE);
            frameMedia.setVisibility(View.GONE);
            frameMediaPonderata.setVisibility(View.GONE);
            frameVotoLaurea.setVisibility(View.GONE);

        }else{
            textViewMsgS.setVisibility(View.GONE);
            textViewMedia.setText(String.valueOf(utente.getMedia()));
            textViewMediaPonderata.setText(String.valueOf(utente.getMediaPonderata()));
            textViewVotoLaurea.setText(String.valueOf(utente.getVotoLaurea()));
        }


        return view;
    }
    public static void addUtente(Persona utente) {
        StatisticheFragment.utente = utente;
    }
}