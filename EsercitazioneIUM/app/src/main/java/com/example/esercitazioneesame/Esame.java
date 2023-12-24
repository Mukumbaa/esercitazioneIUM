package com.example.esercitazioneesame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Esame implements Serializable {
    private int voto;
    private String nomeEsame;
    private int cfu;

    public Esame(String nomeEsame,int voto,int cfu){
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


    @Override
    public String toString() {
        return "Esame{" +
                "nome='" + nomeEsame + '\'' +
                ", voto=" + voto +
                ", cfu=" + cfu +
                '}';
    }
}
