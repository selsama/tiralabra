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
    
    public boolean onSama(Sijainti s) {
        if(this.x == s.getX() && this.y == s.getY()) {
            return true;
        }
        return false;
    }
    
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
