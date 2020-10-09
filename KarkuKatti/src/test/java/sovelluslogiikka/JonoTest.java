/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import karkukatti.sovelluslogiikka.apuluokkia.Jono;
import java.util.*;
/**
 *
 * @author salmison
 */
public class JonoTest {
    private Jono<Integer> j;
    private ArrayDeque<Integer> vert;
    
    public JonoTest() {
        j = new Jono();
        vert = new ArrayDeque<>();
    }
    
    @Test
    public void LisaaLoppuunTesti() {
        int koko = 0;
        for (int i = 0; i < 30; i++) {
            j.lisaaLoppuun(i);
            vert.addLast(i);
            koko++;
            assertEquals("Jono palauttaa väärän arvon.", vert.getLast(), j.haeLopusta());
            assertEquals("jonon koko muuttuu väärin", koko, j.getKoko());
        }
    }
    
    @Test
    public void LisaaAlkuunTesti() {
        int koko = 0;
        for (int i = 0; i < 30; i++) {
            j.lisaaAlkuun(i);
            vert.addFirst(i);
            koko++;
            assertEquals("Jono palauttaa väärän arvon.", vert.getFirst(), j.haeAlusta());
            assertEquals("Jonon koko muuttuu väärin", koko, j.getKoko());
        }
    }
    
    private void lisaaJonoihin30() {
        for (int i = 0; i < 30; i++) {
            j.lisaaAlkuun(i);
            vert.addFirst(i);
        }
    }
    
    @Test
    public void PoistaLopustaTesti() {
        this.lisaaJonoihin30();
        int koko = j.getKoko();
        for (int i = 0; i < 20; i++) {
            int oikea = vert.pollLast();
            int a = j.poistaLopusta();
            koko--;
            assertEquals("Jono palauttaa väärän arvon " + i, oikea, a);
            assertEquals("Jonon koko muuttuu väärin", koko, j.getKoko());
        }
    }

    @Test
    public void PoistaAlustaTesti() {
        this.lisaaJonoihin30();
        int koko = j.getKoko();
        for (int i = 0; i < 20; i++) {
            int oikea = vert.pollFirst();
            int a = j.poistaAlusta();
            koko--;
            assertEquals("Jono palauttaa väärän arvon", oikea, a);
            assertEquals("Jonon koko muuttuu väärin", koko, j.getKoko());
        }
    }
    
    @Test
    public void metodejaSekaisinTesti() {
        for (int i = 0; i < 50; i++) {
            j.lisaaLoppuun(i);
            vert.addLast(i);
            if (i % 2 == 0) {
                j.lisaaAlkuun( + 2);
                vert.addFirst( + 2);
            }
            if (i % 7 == 0) {
               assertEquals("Jonon lopusta poistaminen palauttaa väärän arvon", 
                       vert.pollLast(), j.poistaLopusta());
            } else if (i % 5 == 0) {
                assertEquals("Jonon alusta poistaminen palauttaa väärän arvon", 
                        vert.pollFirst(), j.poistaAlusta());
            }
            if (i % 13 == 3) {
                assertEquals("Jonon lopusta hakeminen palauttaa väärän arvon",
                        vert.peekLast(), j.haeLopusta());
            } else if (i % 13 == 7) {
                assertEquals("Jonon alusta hakeminen palauttaa väärän arvon", 
                        vert.peekFirst(), j.haeAlusta());
            }
        }
        while (!vert.isEmpty()) {
            if (vert.peekFirst() % 2 == 0) {
                assertEquals("Jonon alusta poistaminen antaa väärän arvon",
                        vert.pollFirst(), j.poistaAlusta());
            } else {
                assertEquals("Jonon lopusta poistaminen antaa väärän arvon",
                        vert.pollLast(), j.poistaLopusta());
            }
        }
    }
    
 }
