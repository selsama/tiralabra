/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/** Luokka pelitilanteen arvioimiseen ja parhaan siirron valitsemiseen.
 *
 * @author salmison
 */
public class Tekoaly {
    
    /**
     * Metodi laskee parhaan siirron annetussa tilanteessa. Kutsuu minMax-metodia.
     * @param seinat pelilaudan tilanne
     * @param kissa kissan sijainti
     * @param kissaPelaa true, jos siirtoa etsitään kissalle, false jos seinäpalaajalle
     * @return Sijainti, johon siirto kannattaa tehdä
     */
    public Sijainti laskeSiirto(boolean[][] seinat, Sijainti kissa, boolean kissaPelaa) {
        Sijainti[] mahdollisetSiirrot;
        if (kissaPelaa) {
            mahdollisetSiirrot = this.getNaapurit(kissa);
        } else {
            mahdollisetSiirrot = this.getTyhjat(seinat);
        }
        int kuinkaSyvalle = 2;
        Siirto siirto = this.minMax(this.teeUusiTaulukko(seinat), kissa, kissaPelaa, mahdollisetSiirrot, 1, kuinkaSyvalle);
        return siirto.getKohde();
    }
    /**
     * MinMax-algoritmi, joka valitsee parhaan siirron. Algoritmi kutsuu itseään rekursiivisesti ja olettaa joka tasolla, että vuorossa oleva pelaaja tekee itsensä kannalta parhaan siirron. 
     * @param seinat pelilaudan tilanne
     * @param kissa kissan sijainti
     * @param onkoKissanKierros true, jos on kissan vuoro, muuten false
     * @param mihinVoiSiirtaa taulukko mahdollisista siirroista
     * @param moneskoKierros kuinka mones rekursio on menosssa
     * @param montakoKierrostaHalutaan kuinka syvä rekursio halutaan
     * @return paras Siirto tässä tilanteessa (Sijainti ja hyvyysarvo)
     */
    public Siirto minMax(boolean[][] seinat, Sijainti kissa, boolean onkoKissanKierros, Sijainti[] mihinVoiSiirtaa, int moneskoKierros, int montakoKierrostaHalutaan) {
        Siirto paras = null;
        for (int i = 0; i < mihinVoiSiirtaa.length; i++) { // käydään läpi mahdolliset siirtovaihtoehdot
            Sijainti siirronKohde = mihinVoiSiirtaa[i];
            if (this.onkoSiirtoKielletty(seinat, siirronKohde, kissa)) {
                continue;
            }
            Siirto uusi;
            if (moneskoKierros == montakoKierrostaHalutaan) { // jos ollaan tarpeeksi syvällä vaihtoehtopuussa, lasketaan kuinka hyvä siirto on kyseessä laskeTilanteenHyvyys-metodilla
                if (onkoKissanKierros) { //jos on kissan kierros, tämä tarkoittaa että siiroonKohde on kissan uusi sijainti
                    Double hyvyys = this.laskeTilanteenHyvyys(seinat, siirronKohde);
                    uusi = new Siirto(siirronKohde, hyvyys);
                } else { // jos ei ole kissan vuoro, tämä tarkoittaa että siirronKohde on uuden seinän sijainti. tehdään uusi taulukko seinille, ettei algoritmi flippaa
                    boolean[][] uudetSeinat = this.teeUusiTaulukko(seinat);
                    uudetSeinat[siirronKohde.getX()][siirronKohde.getY()] = true;
                    uusi = new Siirto(siirronKohde, this.laskeTilanteenHyvyys(uudetSeinat, kissa));
                }
            } else { // jos halutaan syvemmälle puuhun, kutsutaan minMaxia rekursiivisesti
                if (onkoKissanKierros) { // jos on kissan kierros, tämä tarkoittaa, että kutsuttava kierros on seinäpelaajan kierros, eli siirronKohde on kissan uusi sijainti, ja mahdolliset siirrot ovat kaikki tyhjät ruudut
                    Sijainti[] uudetVaihtoehdot = this.getTyhjat(seinat);
                    uusi = new Siirto(siirronKohde,
                            this.minMax(seinat, siirronKohde, false, uudetVaihtoehdot, moneskoKierros + 1, montakoKierrostaHalutaan).getHyvyys());
                } else { // jos ei ole kissan vuoro, tämä tarkoittaa että kutsuttava kierros on kissan kierros, eli kissa pysyy paikoillaan, seinät ovat muuten samat mutta siirronKohde muutetaan seinäruuduksi, ja vaihtoehdot ovat uuden sijainnin viereiset ruudut
                    boolean[][] uudetSeinat = this.teeUusiTaulukko(seinat);
                    uudetSeinat[siirronKohde.getX()][siirronKohde.getY()] = true;
                    uusi = new Siirto(siirronKohde, 
                            this.minMax(uudetSeinat, kissa, true, this.getNaapurit(kissa), moneskoKierros + 1, montakoKierrostaHalutaan).getHyvyys());
                }
            }
            if (paras == null) { // jos kyseessä on ensimmäinen laskettava arvo tällä tasolla, merkitään se parhaaksi. muuten verrataan olemassaolevaan
                paras = uusi;
            } else if (onkoKissanKierros) { // jos on kissan kierros, valitaan maksimi
                if (uusi.getHyvyys() == 1000) { // jos siirto on jo paras mahdollinen, palautetaan se eikä jatketa rekursiota
                    return uusi;
                }
                if (paras.getHyvyys() < uusi.getHyvyys()) {
                    paras = uusi;
                }
            } else { // jos ei ole kissan vuoro, valitaan minimi
                if (uusi.getHyvyys() == 0) { // jos siirto on jo paras mahdollinen (seinäpelaajalle), palautetaan se eikä jatketa rekursiota
                    return uusi;
                }
                if (paras.getHyvyys() > uusi.getHyvyys()) {
                    paras = uusi;
                }
            }
        }
        if (paras == null) { //jos vaihtoehtoja ei ole, pitää välttää nullPointerException, mutta ei haluta että seuraava kierros missään tapauksessa valitsee tätä vaihtoehtoa
            if (onkoKissanKierros) {
                return new Siirto(null, 1001);
            } else {
                return new Siirto(null, -1);
            }
        }
        return paras;
    }
    
    /**
     * Tarkistaa, onko kissalla jäljellä ulospääsyä.
     * @param seinat taulukko, jossa true edustaa seinäruutua pelilaudalla
     * @param kissa kissan sijainti
     * @return false, jos yhtään reittiä ulos ei ole, muuten true
     */
    public boolean onkoReittejaJaljella(boolean[][] seinat, Sijainti kissa) {
        Lista<Integer> lista = this.etaisyydetUlos(this.leveyshaku(seinat, kissa));
        if (lista.getKoko() == 0) {
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
        Jono<Sijainti> jono = new Jono<>();
        jono.lisaaLoppuun(kissa);
        etaisyys[kissa.getX()][kissa.getY()] = 0;
        vierailtu[kissa.getX()][kissa.getY()] = true;
        while (jono.getKoko() > 0) {
            Sijainti s = jono.poistaAlusta();
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
                jono.lisaaLoppuun(naapurit[i]);
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
    public Lista<Integer> etaisyydetUlos(int[][] etaisyys) {
        Lista<Integer> etaisyydetUlos = new Lista<>();
        int vika = etaisyys.length - 1;
        if (etaisyys[0][0] < 1000) {
            etaisyydetUlos.lisaa(etaisyys[0][0]);
        }
        if (etaisyys[0][vika] < 1000) {
            etaisyydetUlos.lisaa(etaisyys[0][vika]);
        }
        if (etaisyys[vika][0] < 1000) {
            etaisyydetUlos.lisaa(etaisyys[vika][0]);
        }
        if (etaisyys[vika][vika] < 1000) {
            etaisyydetUlos.lisaa(etaisyys[vika][vika]);
        }
        for (int i = 1; i < vika; i++) {
            if (etaisyys[0][i] < 1000) {
                etaisyydetUlos.lisaa(etaisyys[0][i]);
            }
            if (etaisyys[vika][i] < 1000) {
            etaisyydetUlos.lisaa(etaisyys[vika][i]);
            }
            if (etaisyys[i][0] < 1000) {
                etaisyydetUlos.lisaa(etaisyys[i][0]);
            }
            if (etaisyys[i][vika] < 1000) {
                etaisyydetUlos.lisaa(etaisyys[i][vika]);
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
    private Sijainti[] getTyhjat(boolean[][] seinat) {
        Lista<Sijainti> tyhjatLista = new Lista<>();
        for (int i = 0; i < seinat.length; i++) {
            for (int j = 0; j < seinat.length; j++) {
                if (!seinat[i][j]) {
                    tyhjatLista.lisaa(new Sijainti(i, j));
                }
            }
        }
        Sijainti[] tyhjat = new Sijainti[tyhjatLista.getKoko()];
        for (int i = 0; i < tyhjatLista.getKoko(); i++) {
            tyhjat[i] = tyhjatLista.hae(i);
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
     * @return tilanteen hyvyys kissan kannalta. Mitä suurempi, sen parempi. Välillä (0:100).
     */
    public double laskeTilanteenHyvyys(boolean[][] seinat, Sijainti kissa) {
        int max = 1000;
        Lista<Integer> lista = this.etaisyydetUlos(this.leveyshaku(seinat, kissa));
        lista.jarjesta();
        int maara = lista.getKoko();
        if (maara == 0) {
            return 0; // jos kissa ei pääse pois, tilanne on sille huonoin mahdollinen
        }
        int lyhin = lista.hae(0);
        if (lyhin == 0) {
            return max; // jos kissa on laidalla, tilanne on sille paras mahdollinen
        }
        int lyhimmat = 0;
        int summa = 0;
        for (int i = 0; i < lista.getKoko(); i++) {
            summa += lista.hae(i);
            if (lista.hae(i)  == lyhin) {
                lyhimmat++;
            }
        }
        double keskipituus = 1.0 * summa / maara;
        double hyvyys = (25 * lyhin) + (2 * keskipituus) + (1.0 / maara) + (10.0 / lyhimmat);
        if (hyvyys > max) {
            hyvyys = max;
        }
        return max - hyvyys;
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
    
    /**
     * Tekee kopion annetusta taulukosta.
     * @param seinat kopioitava taulukko
     * @return luotu kopio
     */
    private boolean[][] teeUusiTaulukko(boolean [][] seinat) {
        boolean[][] uusi = new boolean[seinat.length][seinat.length];
        for (int i = 0; i < seinat.length; i++) {
            for (int j = 0; j < seinat.length; j++) {
                uusi[i][j] = seinat[i][j];
            }
        }
        return uusi;
    }
}
