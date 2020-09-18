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
    
    public Sijainti laskeSiirto(boolean[][] seinat, Sijainti kissa, boolean kissaPelaa) {
        Sijainti[] mahdollisetSiirrot = this.getNaapurit(kissa);
        int kuinkaSyvalle = 1;
        Siirto siirto = this.minMax(seinat, kissa, kissaPelaa, mahdollisetSiirrot, 1, kuinkaSyvalle);
        return siirto.getKohde();
    }
    /**
     * 
     * @param seinat
     * @param kissa
     * @param onkoKissanKierros
     * @param mihinVoiSiirtaa
     * @param moneskoKierros
     * @param montakoKierrostaHalutaan
     * @return 
     */
    public Siirto minMax(boolean[][] seinat, Sijainti kissa, boolean onkoKissanKierros, Sijainti[] mihinVoiSiirtaa, int moneskoKierros, int montakoKierrostaHalutaan) {
        Siirto paras = null;
        for (int i = 0; i < mihinVoiSiirtaa.length; i++) {
            Sijainti s = mihinVoiSiirtaa[i];
            if (this.onkoSiirtoKielletty(seinat, s, kissa)) {
                continue;
            }
            Siirto uusi;
            if (moneskoKierros == montakoKierrostaHalutaan) {
                if (onkoKissanKierros) {
                    Double hyvyys = this.laskeTilanteenHyvyys(seinat, s);
                    uusi = new Siirto(s, hyvyys);
                } else {
                    boolean[][] uudetSeinat = seinat;
                    uudetSeinat[s.getX()][s.getY()] = true;
                    uusi = new Siirto(s, this.laskeTilanteenHyvyys(uudetSeinat, kissa));
                }
            } else {
                if (onkoKissanKierros) {
                    uusi = this.minMax(seinat, s, false, 
                            this.getTyhjät(seinat), moneskoKierros + 1, montakoKierrostaHalutaan);
                } else {
                    boolean[][] uudetSeinat = seinat;
                    uudetSeinat[s.getX()][s.getY()] = true;
                    uusi = this.minMax(uudetSeinat, kissa, true, 
                            this.getNaapurit(s), moneskoKierros + 1, montakoKierrostaHalutaan);
                }
            }
            if (paras == null) {
                paras = uusi;
            } else if (onkoKissanKierros) {
                if (paras.getHyvyys() < uusi.getHyvyys()) {
                    paras = uusi;
                }
            } else {
                if (paras.getHyvyys() > uusi.getHyvyys()) {
                    paras = uusi;
                }
            }
        }
        return paras;
    }
    
    public boolean onkoReittejaJaljella(boolean[][] seinat, Sijainti kissa) {
        ArrayList<Integer> lista = this.etaisyydetUlos(this.leveyshaku(seinat, kissa));
        if (lista.size() == 0) {
            return false;
        }
        return true;
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
    
    /**
     * Tekee taulukon annetun sijainnin viereisistä sijainneista. Ei huomioi, ovatko naapurit seinää tai ulkona alueelta.
     * @param s Annettu sijainti
     * @return Taulukko sijainnin viereisistä sijainneista
     */
    private Sijainti[] getNaapurit(Sijainti s) {
        Sijainti[] naapurit = new Sijainti[4];
        naapurit[0] = new Sijainti(s.getX() - 1, s.getY());
        naapurit[1] = new Sijainti(s.getX() + 1, s.getY());
        naapurit[2] = new Sijainti(s.getX(), s.getY() - 1);
        naapurit[3] = new Sijainti(s.getX(), s.getY() + 1);
        return naapurit;
    }
    
    /**
     * Tekee taulukon annetun alueen tyhjistä ruuduista.
     * @param seinat
     * @return 
     */
    private Sijainti[] getTyhjät(boolean[][] seinat) {
        ArrayList<Sijainti> tyhjatLista = new ArrayList<>();
        for (int i = 0; i < seinat.length; i++) {
            for (int j = 0; j < seinat.length; j++) {
                if (!seinat[i][j])
                    tyhjatLista.add(new Sijainti(i, j));
            }
        }
        Sijainti[] tyhjat = new Sijainti[tyhjatLista.size()];
        for (int i = 0; i < tyhjatLista.size(); i++) {
            tyhjat[i] = tyhjatLista.get(i);
        }
        return tyhjat;
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
        int maara = lista.size();
        if (maara == 0) {
            return -100; // jos kissa ei pääse pois, tilanne on sille huonoin mahdollinen
        }
        int lyhin = lista.get(0);
        if (lyhin == 0) {
            return 100; // jos kissa on laidalla, tilanne on sille paras mahdollinen
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
    
    /**
     * Tarkistaa, onko annettu siirto sallittu.
     * @param seinat pelilaudan tilanne
     * @param s Sijainti, johon siirto tehtäisiin
     * @param kissa kissan sijainti
     * @return true, jos siirto on kielletty, muuten false
     */
    private boolean onkoSiirtoKielletty(boolean[][] seinat, Sijainti s, Sijainti kissa) {
        if (s.getX() < 0 || s.getX() >= seinat.length) {
            return true;
        } else if (s.getY() < 0 || s.getY() >= seinat.length) {
            return true;
        }
        if (seinat[s.getX()][s.getY()]) {
            return true;
        }
        if (kissa.onSama(s)) {
            return true;
        }
        return false;
    }
}
