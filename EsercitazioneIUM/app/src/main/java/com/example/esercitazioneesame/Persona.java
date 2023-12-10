package com.example.esercitazioneesame;

import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;


public class Persona implements Serializable {


    private String nome;
    private String cognome;
    private String password;
    private String dataNascita;
    private Boolean esistenza;
    private int ID;
    private ArrayList<Esame> libretto =  new ArrayList<>();
//            =
//            new ArrayList<>(Arrays.asList(
//            new Esame(30,"Analisi 1",9),
//            new Esame(26,"Programmazione 1",12),
//            new Esame(18,"ALF",6),
//            new Esame(21,"EDI",6),
//            new Esame(30,"Calcolo e metodo scientifico",9),
//            new Esame(30,"nome lunghissimissimissimo incredibile gigante",9),
//            new Esame(30,"Analisi 1",9),
//            new Esame(26,"Programmazione 1",12),
//            new Esame(18,"ALF",6),
//            new Esame(21,"EDI",6),
//            new Esame(30,"Calcolo e metodo scientifico",9)
//
//    ));

    private static ArrayList<Persona> persone = new ArrayList<>(Arrays.asList(
            new Persona("Gabriele","Lippolis","password","06-12-2002",true),
            new Persona("Edoardo","Lippolis","password","21-11-2012",true),
            new Persona("Vito","Lippolis","password","10-02-1966",true),
            new Persona("Silvia","Garau","password","08-12-1973",true)
    ));


    public Persona(){
       this.esistenza = true;
       this.ID = getID();
    };

    public Persona(String nome, String cognome, String password, String dataNascita, Boolean esistenza){
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.dataNascita = dataNascita;
        this.esistenza = esistenza;
        this.ID = getID();
    }
    public String getNome(){
        return this.nome;
    }
    public String getCognome(){
        return this.cognome;
    }
    public String getPassword(){
        return this.password;
    }
    public String getDataNascita(){
        return this.dataNascita;
    }
    public Boolean getEsistenza(){
        return this.esistenza;
    }
    public void setNome(){
        this.nome = nome;
    }
    public void setCognome(){
        this.cognome = cognome;
    }
    public void setPassword(){
        this.password = password;
    }
    public void setDataNascita(){
        this.dataNascita = dataNascita;
    }

    public static Persona findPersona(String nome, String cognome, String password){
        for (int i = 0; i< persone.size(); i++){
            if (nome.equals(persone.get(i).nome) && cognome.equals(persone.get(i).cognome)){
                if (!password.equals(persone.get(i).password)){
                        return new Persona(persone.get(i).getNome(),persone.get(i).getCognome(),"null","null",false);
                }else{
                    return persone.get(i);
                }
            }
        }
        return new Persona("null","null","null","null",false);
    }
    public boolean checkUtenteEsistente(){
        for (Persona persona : Persona.persone) {
            if (persona.getNome().equals(this.getNome()) && persona.getCognome().equals(this.getCognome()) && persona.getPassword().equals(this.getPassword()) && persona.getDataNascita().equals(this.getDataNascita()))
            {
                return true;
            }
        }
        return false;
    }
    public void aggiungiUtente(){
        Persona.persone.add(this);
    }
    public int getID(){


        //


        if (this.ID != 0){
            return this.ID;
        }

        String id = "";
        char[] str = (this.getNome()+this.getCognome()+this.getDataNascita()).toCharArray();

        for (char c:str) {
            id += (int)c;
        }
        id = id.substring(this.getNome().length(),this.getNome().length()+3) + id.substring(this.getCognome().length(),this.getCognome().length()+3) + id.substring(this.getDataNascita().length(),this.getDataNascita().length()+2);

        this.ID = Integer.parseInt(id);
        return this.ID;
    }
    public void addEsame(Esame e){
        libretto.add(e);
    }
    public ArrayList<Esame> getLibretto(){
        return libretto;
    }
    public void eliminaEsame(Esame e){
        for (Esame es:
             libretto) {
            if(e.getVoto() == es.getVoto() && e.getCfu() == es.getCfu() && e.getNomeEsame().equals(es.getNomeEsame())){
                libretto.remove(es);
                return;
            }
        }
    }
    public void eliminaEsame(int pos){
        libretto.remove(pos);
    }
    public void modificaEsame(Esame e, int posizione){
        Log.d("SIUM",e.getNomeEsame()+String.valueOf(posizione));
        libretto.set(posizione,e);
        Log.d("SIUM",":"+libretto.get(posizione).getNomeEsame());
    }
    public double getMedia(){
        double media = 0;
        for (Esame e: libretto) {
            media += e.getVoto();
        }
        media = media/libretto.size();

        return (double) Math.round(media * 1000) / 1000;
    }
    public double getMediaPonderata(){
        double media = 0;
        int cfuu = 0;
        for (Esame e: libretto) {
            media += e.getVoto()*e.getCfu();
            cfuu += e.getCfu();
        }
        media = media/cfuu;
        return (double) Math.round(media * 1000) / 1000;
    }
    public double getVotoLaurea(){
        double media = 0;
        int cfuu = 0;
        for (Esame e: libretto) {
            media += e.getVoto()*e.getCfu();
            cfuu += e.getCfu();
        }
        media = (media/cfuu)*110/30;
        return (double) Math.round(media * 1000) / 1000;
    }
    public void setLibretto(ArrayList<Esame> l){
        this.libretto = l;
    }
}
