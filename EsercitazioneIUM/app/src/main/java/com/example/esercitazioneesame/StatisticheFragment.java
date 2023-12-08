package com.example.esercitazioneesame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StatisticheFragment extends Fragment {

    private static Persona utente;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistiche, container, false);
    }
    public static void addUtente(Persona utente) {
        StatisticheFragment.utente = utente;
    }
}