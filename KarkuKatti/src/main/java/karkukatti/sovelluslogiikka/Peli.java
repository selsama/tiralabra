/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

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
    
    /**
     * Luo uuden Peli-instanssin annetussa koossa.
     * @param koko Haluttu pelialueen koko
     */
    public Peli(int koko) {
        seinat = new boolean[koko][koko];
        kissanVuoro = false;
        peliOhi = false;
        kissanSijainti = new Sijainti(koko / 2, koko / 2);
        tekoaly = new Tekoaly();
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
     */
    private void tekoalyPelaa() {
        Sijainti s = tekoaly.laskeSiirto();
        if (kissanVuoro) {
            this.siirraKissaa(s);
        } else {
            this.teeSeina(s);
        }
        this.vaihdaVuoroa();
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
    private boolean voittikoKissa() {
        if (kissanSijainti.getX() == 0 || kissanSijainti.getX() == seinat.length - 1) {
            return true;
        }
        if (kissanSijainti.getY() == 0 || kissanSijainti.getY() == seinat.length - 1) {
            return true;
        }
        return false;
    }
    
    private boolean havisikoKissa() {
        this.tekoaly.leveyshaku(seinat, kissanSijainti);
        //if(noRoutes) --> return true
        return false;
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
}
