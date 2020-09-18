/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import karkukatti.sovelluslogiikka.*;
import java.util.*;

/**
 *
 * @author salmison
 */
public class TekoalyTest {
    Tekoaly aly;
    
    public TekoalyTest() {
        this.aly = new Tekoaly();
    }
    
    @Test
    public void leveyshakuTesti() {
        boolean[][] s = new boolean[3][3];
        int[][] oikea = new int[3][3];
        oikea[0][0] = 2;
        oikea[1][0] = 1;
        oikea[2][0] = 2;
        oikea[0][1] = 1;
        oikea[1][1] = 0;
        oikea[2][1] = 1;
        oikea[0][2] = 2;
        oikea[1][2] = 1;
        oikea[2][2] = 2;
        int[][] vastaus = aly.leveyshaku(s, new Sijainti(1,1));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("Leveyshaku antaa väärän arvon ruudulle (" + i + "," + j + ")", 
                        oikea[i][j], vastaus[i][j]);
            }
        }
    
    }
    
    @Test
    public void etaisyydetUlosTesti() {
        int[][] etaisyydet = new int[5][5];
        etaisyydet[0][0] = 1000;
        etaisyydet[2][4] = 1000;
        etaisyydet[3][0] = 10;
        etaisyydet[4][1] = 12;
        etaisyydet[3][3] = 30;
        ArrayList<Integer> lista = aly.etaisyydetUlos(etaisyydet);
        assertEquals("etaisyydetUlos antaa väärän kokoisen listan", 14, lista.size());
        int summa = 0;
        for (int i: lista) {
            summa+= i;
        }
        assertEquals("etaisyydetUlos antaa väärän listan", 22, summa);
    }
    
}
