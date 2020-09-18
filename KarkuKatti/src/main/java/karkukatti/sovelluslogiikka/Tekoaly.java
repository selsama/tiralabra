/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

import java.util.*;
/**
 *
 * @author salmison
 */
public class Tekoaly {
    
    public Sijainti laskeSiirto() {
        return new Sijainti(0, 0);
    }
    
    /**
     * Muodostaa leveyshaulla taulukon, jossa on etäisyys annetusta ruudusta kuhunkin muuhun ruutuun. Jos ruutuun ei päästä, sen etäisyydeksi jää 1000.
     * @param seinat taulukko, jossa true-edustaa seinäruutuja labyrintissa
     * @param kissa aloitussijainti
     * @return taulukko, jossa on etäisyys aloitussijainnista kuhunkin muuhun taulukon sijaintiin. Jos sijaintiin ei ole reittiä, sen arvo on 1000.
     */
    public int[][] leveyshaku(boolean[][] seinat, Sijainti kissa) {
        int[][] etaisyys = this.teeTuhatTaulukko(seinat.length);
        boolean[][] vierailtu = new boolean[seinat.length][seinat.length];
        ArrayDeque<Sijainti> jono = new ArrayDeque<>();
        jono.add(kissa);
        etaisyys[kissa.getX()][kissa.getY()] = 0;
        vierailtu[kissa.getX()][kissa.getY()] = true;
        while (!jono.isEmpty()) {
            Sijainti s = jono.getFirst();
            jono.removeFirst();
            Sijainti[] naapurit = this.getNaapurit(s);
            for (int i = 0; i < naapurit.length; i++) {
                int x = naapurit[i].getX();
                int y = naapurit[i].getY();
                if (x < 0 || x >= etaisyys.length || y < 0 || y >= etaisyys.length) {
                    continue;
                }
                if (vierailtu[x][y] || seinat[x][y]) {
                    continue;
                }
                jono.addLast(naapurit[i]);
                vierailtu[x][y] = true;
                etaisyys[x][y] = etaisyys[s.getX()][s.getY()] + 1;
            }
        }
        return etaisyys;
    }
    
    /**
     * Muodostaa annetusta etaisyys-taulukosta listan saavutettavissa olevien reunaruutujen etäisyyksistä. Jos etäisyys ruutuun on 1000 tai yli, sitä ei lisätä listalle.
     * @param etaisyys tutkittava taulukko
     * @return Lista, jossa on etäisyys jokaiseen saavutettavissa olevaan reunaruutuun.
     */
    public ArrayList<Integer> etaisyydetUlos(int[][] etaisyys) {
        ArrayList<Integer> etaisyydetUlos = new ArrayList<>();
        int vika = etaisyys.length - 1;
        if (etaisyys[0][0] < 1000) {
            etaisyydetUlos.add(etaisyys[0][0]);
        }
        if (etaisyys[0][vika] < 1000) {
            etaisyydetUlos.add(etaisyys[0][vika]);
        }
        if (etaisyys[vika][0] < 1000) {
            etaisyydetUlos.add(etaisyys[vika][0]);
        }
        if (etaisyys[vika][vika] < 1000) {
            etaisyydetUlos.add(etaisyys[vika][vika]);
        }
        for (int i = 1; i < vika; i++) {
            if (etaisyys[0][i] < 1000) {
                etaisyydetUlos.add(etaisyys[0][i]);
            }
            if (etaisyys[vika][i] < 1000) {
            etaisyydetUlos.add(etaisyys[vika][i]);
            }
            if (etaisyys[i][0] < 1000) {
                etaisyydetUlos.add(etaisyys[i][0]);
            }
            if (etaisyys[i][vika] < 1000) {
                etaisyydetUlos.add(etaisyys[i][vika]);
            }
        }
        return etaisyydetUlos;
    }
    
    private Sijainti[] getNaapurit(Sijainti s) {
        Sijainti[] naapurit = new Sijainti[4];
        naapurit[0] = new Sijainti(s.getX() - 1, s.getY());
        naapurit[1] = new Sijainti(s.getX() + 1, s.getY());
        naapurit[2] = new Sijainti(s.getX(), s.getY() - 1);
        naapurit[3] = new Sijainti(s.getX(), s.getY() + 1);
        return naapurit;
    }
    
    /**
     * Tekee annetun kokoisen taulukon, jossa jokainen arvo on 1000.
     * @param koko 
     * @return 
     */
    private int[][] teeTuhatTaulukko(int koko) {
        int[][] t = new int[koko][koko];
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                t[i][j] = 1000;
            }
        }
        return t;
    }
    
    /**
     * Laskee annetun tilanteen hyvyyden kissan kannalta. Mitä suurempi arvo, sitä parempi.
     * @param seinat Pelilaudan tilanne, true merkitsee seinäruutua
     * @param kissa Kissan sijainti
     * @return tilanteen hyvyys kissan kannalta. Mitä suurempi, sen parempi. Välillä (-100:100).
     */
    private double laskeTilanteenHyvyys(boolean[][] seinat, Sijainti kissa) {
        ArrayList<Integer> lista = this.etaisyydetUlos(this.leveyshaku(seinat, kissa));
        Collections.sort(lista);
        int lyhin = lista.get(0);
        if (lyhin == 0) {
            return 100; // jos kissa on laidalla, tilanne on sille paras mahdollinen
        }
        int maara = lista.size();
        if (maara == 0) {
            return -100; // jos kissa ei pääse pois, tilanne on sille huonoin mahdollinen
        }
        int summa = 0;
        for (int i: lista) {
            summa += i;
        }
        double keskipituus = 1.0 * summa / maara;
        double hyvyys = (3 * maara) - (2 * lyhin) - (1.5 * keskipituus);
        if (hyvyys > 100) {
            hyvyys = 100;
        }
        if (hyvyys < -100) {
            hyvyys = -100;
        }
        return hyvyys;
    }
}
