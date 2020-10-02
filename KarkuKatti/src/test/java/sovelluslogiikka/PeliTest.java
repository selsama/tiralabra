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
        peli = new Peli(5, 2);
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
        peli = new Peli(5, 1);
        assertTrue("Peli ei tee ihmisen siirtoa, vaikka pitäisi", peli.reagoiKlikkaukseen(2, 1));
        Sijainti s = peli.tekoalyPelaa();
        assertFalse("Tekoäly ei tee siirtoa, vaikka pitäisi (seinillä)", s == null);
        boolean [][] seinat = peli.getSeinat();
        assertTrue("Peli ei tehnyt seinää tekoälyn valitsemaan paikkaan", seinat[s.getX()][s.getY()]);
        assertEquals("Tekoäly tekee siirron, vaikka on kissan (ihmisen) vuoro", null, peli.tekoalyPelaa());
        
        peli = new Peli(5, 0);
        assertEquals("Tekoäly tekee siirron, vaikka on seinien (ihmisen) vuoro", null, peli.tekoalyPelaa());
        peli.reagoiKlikkaukseen(1,1);
        s = peli.tekoalyPelaa();
        assertFalse("Tekoäly ei tee siirtoa, vaikka pitäisi (kissalla)", s == null);
        assertTrue("Peli ei siirtänyt kissaa tekoälyn valitsemaan paikkaan", s.onSama(peli.getKissanSijainti()));
        
        peli = new Peli(5,2);
        assertEquals("Tekoäly tekee siirron, vaikka sen ei pitäisi pelata ollenkaan", null, peli.tekoalyPelaa());        
    }
    
}
