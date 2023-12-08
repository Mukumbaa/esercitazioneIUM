package com.example.esercitazioneesame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Esame> data;

    public CustomAdapter(Context context, List<Esame> data) {
        this.context = context;
        this.data = data;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textViewNomeEsame = convertView.findViewById(R.id.textViewNomeEsame);
        TextView textViewVoto = convertView.findViewById(R.id.textViewVoto);
        TextView textViewCfu = convertView.findViewById(R.id.textViewCfu);

        Esame esame = data.get(position);
        textViewNomeEsame.setText(esame.getNomeEsame());
        textViewVoto.setText("Voto: " + String.valueOf(esame.getVoto()));
        textViewCfu.setText("CFU: " + String.valueOf(esame.getCfu()));

        return convertView;
    }
}

