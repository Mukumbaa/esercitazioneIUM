package com.example.esercitazioneesame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Esame {
    private int voto;
    private String nomeEsame;
    private int cfu;
    private static Map<Integer, ArrayList<Esame>> libretto = new HashMap<>();

    public Esame(int voto,String nomeEsame,int cfu){
        this.voto = voto;
        this.nomeEsame = nomeEsame;
        this.cfu = cfu;
    }

    int getVoto(){
        return this.voto;
    }
    int getCfu(){
        return this.cfu;
    }
    String getNomeEsame(){
        return this.nomeEsame;
    }
    void setVoto(int voto){
        this.voto = voto;
    }
    void setCfu(int cfu){
        this.cfu = cfu;
    }
    void setNomeEsame(String nomeEsame){
        this.nomeEsame = nomeEsame;
    }



}
