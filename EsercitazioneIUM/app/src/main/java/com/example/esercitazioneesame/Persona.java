package com.example.esercitazioneesame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
    public void modificaEsame(int posizione,Esame e){
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
    public static void eliminaUtente(Persona utente){
        for (int i = 0; i<persone.size(); i++){
            if(persone.get(i).getMatricola().equals(utente.getMatricola())){
                persone.remove(i);
            }
        }
    }
    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", matricola='" + matricola + '\'' +
                ", password='" + password + '\'' +
                ", elencoEsami=" + libretto +
                '}';
    }
    public static Persona fromString(String data) {
        String[] parts = data.split(", ");
        String nome = parts[0].substring(parts[0].indexOf('=') + 1).replace("'","");
        String cognome = parts[1].substring(parts[1].indexOf('=') + 1).replace("'","");
        String dataNascita = parts[2].substring(parts[2].indexOf('=') + 1).replace("'","");
        String matricola = parts[3].substring(parts[3].indexOf('=') + 1).replace("'","");
        String password = parts[4].substring(parts[4].indexOf('=') + 1).replace("'","");


        // Considera anche la gestione dell'elenco degli esami, potresti usare altre suddivisioni del formato.

        return new Persona(nome, cognome, matricola, password, dataNascita, true);
    }
/*    public void savePersona(Context context){
        try {
            String content = this.toString();
            FileOutputStream fos = context.openFileOutput(this.getMatricola()+".txt", Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Persona readPersona(Context context,String nomeFile){

        String content = "";

        try {
            FileInputStream fis = context.openFileInput(nomeFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            content = sb.toString();
            fis.close();

            Log.d("PROVA",Persona.fromString(content).toString()+"SIUM");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Persona persona = Persona.fromString(content);

        // Recupera l'elenco degli esami dalla stringa e crea gli oggetti Esame
        String esamiString = content.substring(content.indexOf("[") + 1, content.lastIndexOf("]"));
        String[] esamiArray = esamiString.split(", ");
        for (int i = 0;i<esamiArray.length;i+=3){
            persona.addEsame(
                Esame.fromString(esamiArray[i],esamiArray[i+1],esamiArray[i+2])
            );
        }
        Log.d("PROVAA",persona.toString());

        return persona;
    }*/
        /*
Persona{nome='Gabriele',cognome='Lippolis',dataNascita='06/12/02',matricola='66137', password='password',elencoEsami=[Esame{nome='Algoritmi e strutture dati',voto=25, cfu=9}, Esame{nome='Analisi matematica',voto=27, cfu=9}, Esame{nome='Architettura degli elaboratori', voto=30, cfu=6}, Esame{nome='Fisica e metodo scientifico', voto=25, cfu=6}, Esame{nome='Fondamenti di informatica', voto=25, cfu=6}, Esame{nome='Matematica discreta', voto=28, cfu=9}, Esame{nome='Programmazione 1', voto=26, cfu=12}, Esame{nome='Automi e linguaggi formali', voto=18, cfu=6}, Esame{nome='Colcolo scientifico e metodi numerici', voto=29, cfu=6}, Esame{nome='Dati e modelli', voto=30, cfu=6}, Esame{nome='Elementi di economia e diritto per informatici', voto=21, cfu=6}, Esame{nome='Fondamenti di programmazione web', voto=29, cfu=6}, Esame{nome='Programmazione 2', voto=30, cfu=9}, Esame{nome='Reti di calcolatori', voto=28, cfu=9}, Esame{nome='Sistemi operativi', voto=23, cfu=12}]}
*/
}
