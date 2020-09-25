/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/**
 *
 * @author salmison
 * @param <T>
 */
public class Lomitusjarjestaja<T extends Comparable> {
    
    private T[] taulukko;
    private T[] apu;
    
    public Lomitusjarjestaja(int koko, T[] taulukko) {
        this.taulukko = taulukko;
        apu = (T[]) new Comparable[koko];
    }
    
    public void jarjesta(int a, int b) {
        if (a == b) {
            return;
        }
        int k = (a + b) / 2;
        jarjesta(a, k);
        jarjesta(k + 1, b);
        lomita(a, k, k + 1, b);
    }
    
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
        for( int i = a; i <= b; i++) {
            taulukko[i] = apu[i];
        }
    }    
    
}
