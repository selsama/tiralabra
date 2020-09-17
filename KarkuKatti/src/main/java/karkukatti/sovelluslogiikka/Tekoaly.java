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
        return new Sijainti(0,0);
    }
    
    public int[][] leveyshaku(boolean[][] seinat, Sijainti kissa) {
        int[][] etaisyys = new int[seinat.length][seinat.length];
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
    
    private Sijainti[] getNaapurit(Sijainti s) {
        Sijainti[] naapurit = new Sijainti[4];
        naapurit[0] = new Sijainti(s.getX() - 1, s.getY());
        naapurit[1] = new Sijainti(s.getX() + 1, s.getY());
        naapurit[2] = new Sijainti(s.getX(), s.getY() - 1);
        naapurit[3] = new Sijainti(s.getX(), s.getY() + 1);
        return naapurit;
    }
}
