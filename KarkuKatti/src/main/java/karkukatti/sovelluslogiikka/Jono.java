/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/**
 * Luokka jonon käsittelemiseen.Luokka tarjoaa metodit jonon loppuun ja alkuun lisäämiseen, ja alusta ja lopusta hakemiseen ja poistamiseen. Kaikki operaatiot toimivat keskimäärin ajassa O(1).
 * @author salmison
 * @param <T> jonoon lisättävän olion tyyppi
 */
public class Jono<T> {
    private T[] taulukko;
    private int eka, vika, muistissa;
    
    public Jono() {
        taulukko = (T[]) new Object[10];
        eka = 1;
        vika = 0;
        muistissa = 0;
    }
    
    /**
     * Lisää jonon loppuun uuden arvon. Suurentaa varattua taulukkoa tarvittaessa.
     * @param uusi 
     */
    public void lisaaLoppuun(T uusi) {
        vika++;
        if (vika == taulukko.length) {
            vika = 0;
        }        
        taulukko[vika] = uusi;
        muistissa++;
        this.suurennaTaulukkoaJosTarve();
    }
    
    /**
     * Lisää jonon alkuun uuden arvon. Suurentaa taulukkoa tarvittaessa.
     * @param uusi 
     */
    public void lisaaAlkuun(T uusi) {
        eka--;
        if (eka < 0) {
            eka = taulukko.length - 1;
        }
        taulukko[eka] = uusi;
        muistissa++;
        this.suurennaTaulukkoaJosTarve();
    }
    
    /**
     * Hakee, mutta ei poista, jonon ensimmäisen arvon.
     * @return Jonon ensimmäinen arvo tai null, jos jono on tyhjä
     */
    public T haeAlusta() {
        if (muistissa == 0) {
            return null;
        }
        return taulukko[eka];
    }
    
    /**
     * Hakee, ei poista, jonon viimeisen arvon.
     * @return Jonon viimeinen arvo tai null, jos jono on tyhjä
     */
    public T haeLopusta() {
        if (muistissa == 0) {
            return null;
        }
        return taulukko[vika];
    }
    
    /**
     * Palauttaa ja poistaa jonon ensimmäisen arvon. Pienentää varattua taulukkoa tarvittaessa.
     * @return Arvo, joka oli jonossa ensimmäisenä. Null jos jono oli tyhjä.
     */
    public T poistaAlusta() {
        if (muistissa == 0) {
            return null;
        }
        T poistettu = taulukko[eka];
        eka++;
        if (eka == taulukko.length) {
            eka = 0;
        }
        muistissa--;
        this.pienennaTaulukkoaJosTarve();
        return poistettu;
    }
    
    /**
     * Palauttaa ja poistaa jonon viimeisen arvon. Pienentää varattua taulukkoa tarvittaessa.
     * @return Arvo, joka oli viimeisenä. Null jos jono oli tyhjä.
     */
    public T poistaLopusta() {
        if (muistissa == 0) {
            return null;
        }
        T poistettu = taulukko[vika];
        vika--;
        if (vika < 0) {
            vika+= taulukko.length;
        }
        muistissa--;
        this.pienennaTaulukkoaJosTarve();
        return poistettu;
    }
    
    /**
     * Palautta jonossa olevien arvojen määrän.
     * @return 
     */
    public int getKoko() {
        return muistissa;
    }
    
    /**
     * Tarkistaa, tarvitseeko taulukko lisää tilaa. Kaksinkertaistaa taulukon koon, jos edellinen oli täynnä.
     */
    private void suurennaTaulukkoaJosTarve() {
        if (muistissa < taulukko.length) {
            return;
        }
        T[] uusi = (T[]) new Object[taulukko.length * 2];
        int a = eka;
        for (int i = 0; i < muistissa; i++) {
            if (a == taulukko.length) {
                a = 0;
            }
            uusi[i] = taulukko[a];
            a++;
        }
        taulukko = uusi;
        eka = 0;
        vika = muistissa - 1;
        
    }
    
    /**
     * Tarkistaa, tarvitseeko taulukkoa pienentää. Puolittaa taulukon koon, jos vanhasta oli käytössä vain neljäsosa.
     */
    private void pienennaTaulukkoaJosTarve() {
        if (muistissa > taulukko.length / 4) {
            return;
        }
        T[] uusi = (T[]) new Object[taulukko.length / 2];
        int a = eka;
        for (int i = 0; i < muistissa; i++) {
            if (a == taulukko.length) {
                a = 0;
            }
            uusi[i] = taulukko[a];
            a++;
        }
    }
    
}
