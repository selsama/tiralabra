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
    
    public void lisaaLoppuun(T uusi) {
        vika++;
        if (vika == taulukko.length) {
            vika = 0;
        }        
        taulukko[vika] = uusi;
        muistissa++;
        this.suurennaTaulukkoaJosTarve();
    }
    
    public void lisaaAlkuun(T uusi) {
        eka--;
        if (eka < 0) {
            eka = taulukko.length - 1;
        }
        taulukko[eka] = uusi;
        muistissa++;
        this.suurennaTaulukkoaJosTarve();
    }
    
    public T haeAlusta() {
        if (muistissa == 0) {
            return null;
        }
        return taulukko[eka];
    }
    
    public T haeLopusta() {
        if (muistissa == 0) {
            return null;
        }
        return taulukko[vika];
    }
    
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
    
    public int getKoko() {
        return muistissa;
    }
    
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
