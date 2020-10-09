/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

import karkukatti.sovelluslogiikka.apuluokkia.Sijainti;
import karkukatti.sovelluslogiikka.apuluokkia.Lista;

/**
 *
 * @author salmison
 */
public class Peli {
    private boolean[][] seinat;
    private boolean kissanVuoro;
    private Sijainti kissanSijainti;
    private boolean peliOhi;
    private boolean kissaVoitti;
    private Tekoaly tekoaly;
    private boolean tekoalyOhjaaKissaa;
    private boolean tekoalyOhjaaSeinia;
    private Lista<Sijainti> alkuseinat;
    
    /**
     * Luo uuden Peli-instanssin annetussa koossa.
     * @param koko Haluttu pelialueen koko
     * @param pelaajat Haluttu pelaajat vs tietokone-asetus (-1 = tietokone vastaan tietokone, 0 = ihminen vastaan kissa, 1 = ihminen vastaan seinät, 2 = kaksi ihmistä)
     */
    public Peli(int koko, int pelaajat, int montakoSeinaa) {
        seinat = new boolean[koko][koko];
        kissanVuoro = false;
        peliOhi = false;
        kissanSijainti = new Sijainti(koko / 2, koko / 2);
        tekoaly = new Tekoaly();
        if (pelaajat == -1) {
            tekoalyOhjaaKissaa = true;
            tekoalyOhjaaSeinia = true;
        } else if (pelaajat == 0) {
            tekoalyOhjaaKissaa = true;
            tekoalyOhjaaSeinia = false;
        } else if (pelaajat == 1) {
            tekoalyOhjaaKissaa = false;
            tekoalyOhjaaSeinia = true;
            kissanVuoro = true;
        } else {
            tekoalyOhjaaKissaa = false;
            tekoalyOhjaaSeinia = false;
        }
        this.teeAloitus(montakoSeinaa, 2);
    }
    
    /**
     * Peli reagoi klikkaukseen. Eli vuorosta riippuen tekee seinän tai siirtää kissaa, ja vaihtaa pelaajan vuoroa. Jos siirto ei ole sallittu, palauttaa false eikä tee mitään. 
     * @param x klikatun ruudun x-koordinaatti
     * @param y klikatun ruudun y-koordinaatti
     * @return true, jos siirto tehtiin, false jos se ei ollut sallittu
     */
    public boolean reagoiKlikkaukseen(int x, int y) {
        Sijainti s = new Sijainti(x, y);
        if (kissanVuoro) {
            // liikuta kissaa, jos onnistuu; jossei, palauta false
            if (this.siirraKissaa(s)) {
                this.vaihdaVuoroa();
                return true;
            }
            return false;
        } else {
            if (this.teeSeina(s)) {
                this.vaihdaVuoroa();
                return true;
            }
            return false;
        }
    }
    
    /**
     * Peli vaihtaa vuoroa. Eli tarkistaa, loppuiko peli; ja jossei, vaihtaa vuoroa kissalta pelaajalle tai toisinpäin.
     */
    private void vaihdaVuoroa() {
        if (kissanVuoro) {
            if (this.voittikoKissa()) {
                peliOhi = true;
                kissaVoitti = true;
            }
            kissanVuoro = false;
        } else {
            if (this.havisikoKissa()) {
                peliOhi = true;
                kissaVoitti = false;
            }
            kissanVuoro = true;
        }
    }
    
    /**
     * Metodi kutsuu tekoälyä valitsemaan siirron, ja tekee valitun siirron.
     * @return 
     */
    public Sijainti tekoalyPelaa() {
        if (this.onkoPelaajanVuoro()) {
            return null;
        }
        boolean[][] uusi = seinat.clone();
        Sijainti s = tekoaly.laskeSiirto(uusi, kissanSijainti, kissanVuoro);
        if (kissanVuoro) {
            this.siirraKissaa(s);
        } else {
            this.teeSeina(s);
        }
        this.vaihdaVuoroa();
        return s;
    }
    
    /**
     * Muuttaa annetun ruudun seinäksi, mikäli se ei vielä sitä ole, eikä kissa ole siinä
     * @param s ruudun sijainti
     * @return true, jos ruutu muutettiin seinäksi, false jos se oli sitä jo tai kissa oli siellä
     */
    public boolean teeSeina(Sijainti s) {
        if (this.kissanSijainti.onSama(s)) {
            return false;
        }
        if (this.seinat[s.getX()][s.getY()]) {
            return false;
        }
        this.seinat[s.getX()][s.getY()] = true;
        return true;
    }
    
    /**
     *  Peli siirtää kissaa. Metodi tarkistaa ensin, onko siirto sallittu.
     * @param s uusi sijainti
     * @return true, jos siirto tehtiin, false, jos se ei ollut sallittu.
     */
    public boolean siirraKissaa(Sijainti s) {
        if (seinat[s.getX()][s.getY()]) {
            return false;
        }
        if (kissanSijainti.onViereinen(s)) {
            kissanSijainti = s;
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tarkistaa, pääsikö kissa reunaan.
     * @return true, jos kissa voitti, muuten false.
     */
    public boolean voittikoKissa() {
        if (kissanSijainti.getX() == 0 || kissanSijainti.getX() == seinat.length - 1) {
            return true;
        }
        if (kissanSijainti.getY() == 0 || kissanSijainti.getY() == seinat.length - 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tarkistaa, onko kissalla reittejä ulos jäljellä.
     * @return true, jos kissa hävisi (eli ei pääse enää ulos, muuten false
     */
    public boolean havisikoKissa() {
        return (!tekoaly.onkoReittejaJaljella(seinat, kissanSijainti));
    }
    
    public int getKoko() {
        return seinat.length;
    }
    
    public boolean[][] getSeinat() {
        return this.seinat;
    }
    
    public boolean getKissanVuoro() {
        return kissanVuoro;
    }
    
    public Sijainti getKissanSijainti() {
        return kissanSijainti;
    }
    
    public boolean getPeliLoppui() {
        return this.peliOhi;
    }
    
    public boolean getKissaVoitti() {
        return this.kissaVoitti;
    }
    
    public Lista<Sijainti> getAlkuseinat() {
        return this.alkuseinat;
    }
    
    public boolean onkoPelaajanVuoro() {
        if (tekoalyOhjaaKissaa && kissanVuoro) {
            return false;
        } else if (tekoalyOhjaaSeinia && !kissanVuoro) {
            return false;
        }
        return true;
    }
    
    /**
     * Satunnaistaa pelin aloituksen. Tekee kullekin laidalle max n seinää, ja sijoittaa kissan aloittamaan m ruudun säteellä keskikohdasta.
     * @param n 
     */
    private void teeAloitus(int n, int m) {
        alkuseinat = new Lista<>();   
        if (n == 0) {
            return;
        }
        int a = seinat.length / 2 - m;
        kissanSijainti = new Sijainti(a + this.annaSatunnainenLuku(m), a + this.annaSatunnainenLuku(m));
        a = seinat.length;
        while (alkuseinat.getKoko() < n) {
            Sijainti s = new Sijainti(this.annaSatunnainenLuku(a), this.annaSatunnainenLuku(a));
            if (this.teeSeina(s)) {
                alkuseinat.lisaa(s);
            }
        }
    }
    
    /**
     * Antaa pseudorandomin luvun väliltä 0-n.
     * @param n
     * @return 
     */
    private int annaSatunnainenLuku(int n) {
        return (int) (System.nanoTime() % n);
    }
}
