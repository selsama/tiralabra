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
        peli = new Peli(5);
    }
    
    @Test
    public void ReagoiKlikkaukseenTest() {
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
    
}
