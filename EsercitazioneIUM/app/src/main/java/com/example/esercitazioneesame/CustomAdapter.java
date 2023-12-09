package com.example.esercitazioneesame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.LinkOption;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Esame> data;
    private Persona utente;

    public CustomAdapter(Context context, List<Esame> data,Persona Utente) {
        this.context = context;
        this.data = data;
        this.utente = utente;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textViewNomeEsame = convertView.findViewById(R.id.textViewNomeEsame);
        TextView textViewVoto = convertView.findViewById(R.id.textViewVoto);
        TextView textViewCfu = convertView.findViewById(R.id.textViewCfu);
        ImageButton buttonElimina = convertView.findViewById(R.id.buttonElimina);




        Esame esame = data.get(position);
        textViewNomeEsame.setText(esame.getNomeEsame());
        textViewVoto.setText(String.valueOf(esame.getVoto()));
        textViewCfu.setText(esame.getCfu() +"CFU");


        buttonElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(buttonElimina.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, EliminazioneEsameActivity.class);

                intent.putExtra("utente",utente);
                intent.putExtra("esame", esame);
                intent.putExtra("posizione",position);


                context.startActivity(intent);

                // Chiude l'Activity corrente
                ((Activity) context).finish();
            }
        });



        return convertView;
    }

    public void setUtente(Persona utente) {
        this.utente = utente;
    }
}
