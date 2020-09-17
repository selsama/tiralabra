/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka;

/**
 * Luokka sijainnin x- ja y-koordinaatin tallettamiseen ja vertailemiseen.
 */
public class Sijainti {
    private int x;
    private int y;
    
    /**
     * Luo uuden Sijainti-instanssin annetuilla koordinaateilla.
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    /**
     * Tarkistaa, onko annettu sijainti sama.
     * @param s Vertailtava sijainti
     * @return True, jos x- ja y-koordinaatit ovat samat. Muuten false.
     */
    public boolean onSama(Sijainti s) {
        if (this.x == s.getX() && this.y == s.getY()) {
            return true;
        }
        return false;
    }
    
    /**
     * Tarkistaa, onko annettu sijainti sijainnin viereinen ruutu. (ylhäällä, alhaalla, tai sivulla)
     * @param s Sijainti, johon verrataan
     * @return True, jos on, muuten false.
     */
    public boolean onViereinen(Sijainti s) {
        int xEro = Math.abs(x - s.getX());
        int yEro = Math.abs(y - s.getY());
        if (xEro > 1) {
            return false;
        }
        if (yEro > 1) {
            return false;
        }
        if (this.onSama(s)) {
            return false;
        }
        if (xEro == 1 && yEro == 1) {
            return false;
        }
        return true;
    }
}