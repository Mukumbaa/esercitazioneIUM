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
    private String matricola;
    private String password;
    private String dataNascita;
    private Boolean esistenza;
    private int ID;
    private ArrayList<Esame> libretto =  new ArrayList<>();

    private static ArrayList<Persona> persone = new ArrayList<>(Arrays.asList(
            new Persona("Gabriele","Lippolis","66137","password","06/12/02",true),
            new Persona("Edoardo","Lippolis","66138","password","21/11/2012",true),
            new Persona("Vito","Lippolis","66139","password","10/02/1966",true),
            new Persona("Silvia","Garau","66140","password","08/12/1973",true)
    ));


    public Persona(){
       this.esistenza = true;
    };

    public Persona(String nome, String cognome, String matricola,String password, String dataNascita, Boolean esistenza){
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.password = password;
        this.dataNascita = dataNascita;
        this.esistenza = esistenza;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCognome(){
        return this.cognome;
    }
    public String getMatricola(){
        return this.matricola;
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

    public static Persona findPersona(String matricola, String password){
        for (int i = 0; i< persone.size(); i++){
            if (persone.get(i).getMatricola().equals(matricola)){
                if (!password.equals(persone.get(i).password)){
                        return new Persona(persone.get(i).getNome(),persone.get(i).getCognome(),persone.get(i).getMatricola(),"null","null",false);
                }else{
                    return persone.get(i);
                }
            }
        }
        return new Persona("null","null","null","null","null",false);
    }
    public boolean checkUtenteEsistente(){
        for (Persona persona : Persona.persone) {
            if (persona.getMatricola().equals(this.getMatricola()) && persona.getNome().equals(this.getNome()) && persona.getCognome().equals(this.getCognome()) && persona.getDataNascita().equals(this.getDataNascita()))
            {
                return true;
            }
        }
        return false;
    }
    public void aggiungiUtente(){
        Persona.persone.add(this);
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
        libretto.set(posizione,e);
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
