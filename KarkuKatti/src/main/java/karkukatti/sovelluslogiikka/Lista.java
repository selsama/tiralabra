/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/**
 * Taulukkolista-tietorakenne. Sallii tehokkaat lisäykset lopussa ja tarjoaa metodin listan järjestämiseen.
 * @author salmison
 */
public class Lista<T extends Comparable> {
    
    private T[] taulukko;
    private int montakoMuistissa;
    
    public Lista() {
        this.taulukko = (T[]) new Comparable[10];
        this.montakoMuistissa = 0;
    }
    
    public void lisaa(T arvo) {
        if (montakoMuistissa >= taulukko.length) {
            this.teeIsompi();
        }
        taulukko[montakoMuistissa] = arvo;
        montakoMuistissa++;
    }
    
    public T hae(int i) {
        return taulukko[i];
    }
    
    public int getKoko() {
        return montakoMuistissa;
    }
    
    public void jarjesta() {
        Lomitusjarjestaja jarjestaja = new Lomitusjarjestaja<>(montakoMuistissa, taulukko);
        jarjestaja.jarjesta(0, montakoMuistissa - 1);            
    }
    
    private void teeIsompi() {
        T[] uusi = (T[]) new Comparable[taulukko.length * 2];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        taulukko = uusi;
    }
       

}
