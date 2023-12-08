package com.example.esercitazioneesame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class LibrettoFragment extends Fragment {
    private static Persona utente;
    private TextView textViewMsg;
    private Button aggiungiEsame;
    private ListView librettoLista;

    ArrayList<Esame> esami;
    ArrayAdapter<Esame> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_libretto, container, false);

        aggiungiEsame = view.findViewById(R.id.aggiungiEsame);
        textViewMsg = view.findViewById(R.id.textViewMsg);
        librettoLista = view.findViewById(R.id.librettoLista);



        if (utente.getLibretto().size() == 0){
            textViewMsg.setText("Nessun voto registrato");
        }else{
            textViewMsg.setText("Libretto esami");
        }
        ListView librettoLista = view.findViewById(R.id.librettoLista);
        esami = utente.getLibretto();
        // Aggiungi altri esami secondo necessità
        CustomAdapter adapter = new CustomAdapter(getContext(), esami);
        librettoLista.setAdapter(adapter);

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