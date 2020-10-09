/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.testaus;

import karkukatti.sovelluslogiikka.apuluokkia.Sijainti;
import java.util.Arrays;
import karkukatti.sovelluslogiikka.*;

/**
 *
 * @author salmison
 */
public class SuorituskykyTestaaja {
    private static Peli p;
    private static Tekoaly t;
    
    public static void main(String[] args) {
        int toistoja = 20;
        for (int i = 10; i <= 100; i*=10) {
            testaaAlustusta(i, toistoja);
            testaaSiirronValintaa(i, toistoja);
            System.out.println("");
        }
        testaaSiirronValintaa(10, toistoja);
    }
    
    /**
     * Testaa pelin alustusta
     * @param koko
     * @param seinia
     * @param monestiko 
     */
    private static void testaaAlustusta(int koko, int monestiko) {
        alustaPeli(koko, koko);
        long[] ajat = new long[monestiko];
        for (int i = 0; i < monestiko; i++) {
            long t = System.nanoTime();
            alustaPeli(koko, koko);
            t = System.nanoTime() - t;
            ajat[i] = t;
        }
        Arrays.sort(ajat);
        double med = ajat[monestiko / 2] / 1000000.0;
        System.out.println("Pelin alustuksen keston mediaani " + koko + "x" +
                koko + "-laudalla: " + med + " ms");
    }
    
    private static void alustaPeli(int n, int seinia) {
        p = new Peli(n, -1, seinia);
    }
    
    /**
     * Testaa kauanko tekoälyllä kestää valita siirto
     * @param koko
     * @param monestiko 
     */
    private static void testaaSiirronValintaa(int koko, int monestiko) {
        alustaPeli(koko, koko);
        t = new Tekoaly();
        boolean[][] s = p.getSeinat();
        Sijainti k = p.getKissanSijainti();
        long[] ajat = new long[monestiko];
        for (int i = 0; i < monestiko; i++) {
            long t = System.nanoTime();
            valitseSiirto(s, k, i % 2 == 0);
            t = System.nanoTime() - t;
            ajat[i] = t;
        }
        Arrays.sort(ajat);
        double med = ajat[monestiko / 2] / 1000000.0;
        System.out.println("Tekoälyn siirron valinnan keston mediaani " + koko + "x" +
                koko + "-laudalla: " + med + " ms");
    }
    
    private static void valitseSiirto(boolean[][] seinat, Sijainti kissa, boolean kissanVuoro) {
        t.laskeSiirto(seinat, kissa, kissanVuoro);
    }
}
