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
    
    
    public Peli(int koko) {
        seinat = new boolean[koko][koko];
        kissanVuoro = false;
    }
    
    /**
     * Peli reagoi klikkaukseen. Eli vuorosta riippuen tekee seinän tai siirtää kissaa, ja vaihtaa pelaajan vuoroa. Jos siirto ei ole sallittu, palauttaa false eikä tee mitään. 
     * @param x klikatun ruudun x-koordinaatti
     * @param y klikatun ruudun y-koordinaatti
     * @return true, jos siirto tehtiin, false jos se ei ollut sallittu
     */
    public boolean reagoiKlikkaukseen(int x, int y) {
        if(kissanVuoro) {
            // liikuta kissaa, jos onnistuu; jossei, palauta false
            return false;
        }
        else {
            if(this.teeSeina(x, y)) {
                // kissanVuoro = true;
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    /**
     * Muuttaa annetun ruudun seinäksi, mikäli se ei vielä sitä ole
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return true, jos ruutu muutettiin seinäksi, false jos se oli sitä jo
     */
    public boolean teeSeina(int x, int y) {
        if(this.seinat[x][y]) {
            return false;
        }
        this.seinat[x][y] = true;
        return true;
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
}
