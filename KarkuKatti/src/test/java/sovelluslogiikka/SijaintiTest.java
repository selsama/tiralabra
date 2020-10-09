/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import karkukatti.sovelluslogiikka.apuluokkia.Sijainti;

/**
 *
 * @author salmison
 */
public class SijaintiTest {
    
    public SijaintiTest() {
    }
    
    @Test
    public void OnSamaPalauttaaTrueKunSama() {
        Sijainti a = new Sijainti(1, 1);
        Sijainti b = new Sijainti(1, 1);
        assertTrue(a.onSama(b));
        assertTrue(b.onSama(a));
        assertTrue(a.onSama(a));
    }
    
    @Test
    public void OnSamaPalauttaaFalseKunEri() {
        Sijainti a = new Sijainti(2, 3);
        Sijainti b = new Sijainti(1, 2);
        assertFalse(a.onSama(b));
        assertFalse(b.onSama(a));
    }
    
    @Test
    public void OnSamaPalauttaaFalseKunEriPäin() {
        Sijainti a = new Sijainti(3, 5);
        Sijainti b = new Sijainti(5, 3);
        assertFalse(a.onSama(b));
        assertFalse(b.onSama(a));
    }
    
    @Test
    public void OnViereinenPalauttaaFalseKunSama() {
        Sijainti a = new Sijainti(1, 3);
        assertFalse(a.onViereinen(a));
    }
    
    @Test
    public void OnViereinenPalauttaaFalseKunKaukana() {
        Sijainti a = new Sijainti(1,3);
        Sijainti b = new Sijainti(3, 3);
        Sijainti c = new Sijainti(2, 1);
        assertFalse(a.onViereinen(b));
        assertFalse(a.onViereinen(c));
        assertFalse(b.onViereinen(c));
    }
    
    @Test
    public void OnViereinenPalauttaaTrueKunVieressä() {
        Sijainti a = new Sijainti(2, 2);
        Sijainti b = new Sijainti(2, 3);
        Sijainti c = new Sijainti(3, 2);
        Sijainti d = new Sijainti(2, 1);
        Sijainti e = new Sijainti(1, 2);
        assertTrue(a.onViereinen(b));
        assertTrue(a.onViereinen(c));
        assertTrue(a.onViereinen(d));
        assertTrue(a.onViereinen(e));
        
    }
    
}
