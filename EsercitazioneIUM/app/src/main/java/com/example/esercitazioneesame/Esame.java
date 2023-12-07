package com.example.esercitazioneesame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Esame {
    private int voto;
    private String nomeEsame;
    private int cfu;
    private static Map<String, ArrayList<Integer>> libretto = new HashMap<>();

    public Esame(int voto,String nomeEsame,int cfu){
        this.voto = voto;
        this.nomeEsame = nomeEsame;
        this.cfu = cfu;
    }

}
