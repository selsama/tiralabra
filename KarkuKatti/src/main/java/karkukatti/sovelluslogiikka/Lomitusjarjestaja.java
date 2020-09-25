/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/**
 * Luokka tarjoaa metodin annetun taulukon lomitusjärjestämiseen.
 * @author salmison
 * @param <T> järjestettävän taulukon tyyppi
 */
public class Lomitusjarjestaja<T extends Comparable> {
    
    private T[] taulukko;
    private T[] apu;
    
    /**
     * Luo uuden Lomitusjärjestäjä-instanssin. Sille annetaan järjestettävä taulukko ja tieto siitä, kuinka monta alkiota taulukossa on (jos taulukon lopussa on tyhjää, järjestäjä ei huomioi sitä).
     * @param koko
     * @param taulukko 
     */
    public Lomitusjarjestaja(int koko, T[] taulukko) {
        this.taulukko = taulukko;
        apu = (T[]) new Comparable[koko];
    }
    
    /**
     * Järjestää taulukon annetun kohdan lomitusjärjestämisellä. Kutsuu metodia lomita.
     * @param a järjestettävän kohdan alku taulukossa
     * @param b järjestettävän kohdan loppu
     */
    public void jarjesta(int a, int b) {
        if (a == b) {
            return;
        }
        int k = (a + b) / 2;
        jarjesta(a, k);
        jarjesta(k + 1, b);
        lomita(a, k, k + 1, b);
    }
    
    /**
     * Yhdistää taulukon annetuista kohdista.
     * @param a1 ensimmäisen osan alku
     * @param b1 ensimmäisen osan loppu
     * @param a2 toisen osan alku
     * @param b2 toisen osan loppu
     */
    private void lomita(int a1, int b1, int a2, int b2) {
        int a = a1;
        int b = b2;
        for (int i = a; i <= b; i++) {
            if (a2 > b2 || (a1 <= b1 && taulukko[a1].compareTo(taulukko[a2]) < 0)) {
                apu[i] = taulukko[a1];
                a1++;
            } else {
                apu[i] = taulukko[a2];
                a2++;
            }
        }
        for (int i = a; i <= b; i++) {
            taulukko[i] = apu[i];
        }
    }    
    
}
