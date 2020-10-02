/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import karkukatti.sovelluslogiikka.*;

/**
 *
 * @author salmison
 */
public class PeliTest {
    
    private Peli peli;
    
    public PeliTest() {
        peli = new Peli(5, 2, 0);
    }
    
    @Test
    public void ReagoiKlikkaukseenTesti() {
        assertFalse("Antaa tehdä seinän kissan päälle", peli.reagoiKlikkaukseen(2, 2));
        assertTrue("Ei anna tehdä seinää reunaan", peli.reagoiKlikkaukseen(0, 2));
        assertFalse("Antaa siirtää kissaa liian kauas", peli.reagoiKlikkaukseen(0, 0));
        assertFalse("Antaa siirtää kissaa vinottain", peli.reagoiKlikkaukseen(1, 1));
        assertFalse("Antaa olla siirtämättä kissaa", peli.reagoiKlikkaukseen(2, 2));
        assertTrue("Ei anna siirtää kissaa viereiseen", peli.reagoiKlikkaukseen(1, 2));
        assertFalse("Antaa tehdä seinän seinän päälle", peli.reagoiKlikkaukseen(0, 2));
        assertTrue("Ei anna tehdä seinää ruutuun, jossa kissa ei enää ole", peli.reagoiKlikkaukseen(2, 2));
        assertFalse("Antaa siirtää kissan seinän päälle", peli.reagoiKlikkaukseen(0, 2));
    }
    
    @Test
    public void TekoalyPelaaTesti() {
        peli = new Peli(5, 1, 0);
        assertTrue("Peli ei tee ihmisen siirtoa, vaikka pitäisi", peli.reagoiKlikkaukseen(2, 1));
        Sijainti s = peli.tekoalyPelaa();
        assertFalse("Tekoäly ei tee siirtoa, vaikka pitäisi (seinillä)", s == null);
        boolean [][] seinat = peli.getSeinat();
        assertTrue("Peli ei tehnyt seinää tekoälyn valitsemaan paikkaan", seinat[s.getX()][s.getY()]);
        assertEquals("Tekoäly tekee siirron, vaikka on kissan (ihmisen) vuoro", null, peli.tekoalyPelaa());
        
        peli = new Peli(5, 0, 0);
        assertEquals("Tekoäly tekee siirron, vaikka on seinien (ihmisen) vuoro", null, peli.tekoalyPelaa());
        peli.reagoiKlikkaukseen(1,1);
        s = peli.tekoalyPelaa();
        assertFalse("Tekoäly ei tee siirtoa, vaikka pitäisi (kissalla)", s == null);
        assertTrue("Peli ei siirtänyt kissaa tekoälyn valitsemaan paikkaan", s.onSama(peli.getKissanSijainti()));
        
        peli = new Peli(5,2, 0);
        assertEquals("Tekoäly tekee siirron, vaikka sen ei pitäisi pelata ollenkaan", null, peli.tekoalyPelaa());        
    }
    
    private void ymparoiAlue(int n, Sijainti aloitus) {
        Sijainti yla = aloitus;
        Sijainti vasen = aloitus;
        Sijainti oikea = new Sijainti(aloitus.getX() + n - 1, aloitus.getY());
        Sijainti ala = new Sijainti(aloitus.getX(), aloitus.getY() + n - 1);
        
        for (int i = 0; i < n; i++) {
            peli.teeSeina(yla);
            yla = new Sijainti(yla.getX() + 1, yla.getY());
            peli.teeSeina(ala);
            ala = new Sijainti(ala.getX() + 1, ala.getY());
            peli.teeSeina(vasen);
            vasen = new Sijainti(vasen.getX(), vasen.getY() + 1);
            peli.teeSeina(oikea);
            oikea = new Sijainti(oikea.getX(), oikea.getY() + 1);
        }
    }
    
    @Test
    public void PelinLoppumisTesti() {
        peli = new Peli(3, 2, 0);
        this.ymparoiAlue(3, new Sijainti(0,0));
        assertTrue("Peli ei tunnista kissan hävinneen", peli.havisikoKissa());
        peli = new Peli(5, 2, 0);
        peli.siirraKissaa(new Sijainti(1, 2));
        this.ymparoiAlue(3, new Sijainti(0,1));
        assertTrue("Peli ei tunnista kissan hävinneen", peli.havisikoKissa());
        peli = new Peli(3, 2, 0);
        peli.siirraKissaa(new Sijainti(1,0));
        assertTrue("Peli ei tunnista kissan voittaneen", peli.voittikoKissa()); 
    }
    
    @Test
    public void SatunnaistettuAloitusTesti() {
        for (int i = 0; i < 15; i++) {
            peli = new Peli(7, 2, 3);
            Lista<Sijainti> seinat = peli.getAlkuseinat();
            Sijainti kissa = peli.getKissanSijainti();
            assertEquals("Peli tekee väärän määrän seiniä", 3, seinat.getKoko());
            if (kissa.getX() == 0 || kissa.getX() == 6 || kissa.getY() == 6 || kissa.getY() == 0) {
                fail("Kissa aloittaa liian kaukaa");
            }
        }
    }
    
}
