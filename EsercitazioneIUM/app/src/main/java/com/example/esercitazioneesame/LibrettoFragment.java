package com.example.esercitazioneesame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LibrettoFragment extends Fragment {
    private static Persona utente;
    private TextView textViewMsg;
    private TextView textViewLibretto;

    private Button aggiungiEsame;
    private ListView librettoLista;
    private ImageButton buttonDelete;
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
        textViewLibretto = view.findViewById(R.id.textViewLibretto);

        if (utente.getLibretto().size() == 0){
            textViewMsg.setText("Nessun voto registrato");
            textViewLibretto.setVisibility(View.GONE);
        }else{
            textViewLibretto.setText("Libretto esami");
            textViewMsg.setVisibility(View.GONE);
        }
        librettoLista = view.findViewById(R.id.librettoLista);
        librettoLista.setDivider(null);
        librettoLista.setDividerHeight(50);
        esami = utente.getLibretto();

        librettoLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"sium",Toast.LENGTH_LONG);
            }
        });

        // Aggiungi altri esami secondo necessità
        CustomAdapter adapter = new CustomAdapter(getContext(), esami,utente);
        adapter.setUtente(utente);
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