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
public class ListaTest {
    
    public ListaTest() {
        
    }
    
    
    @Test
    public void ListaLisayksetTesti() {
        Lista<Integer> testattava = new Lista<>();
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 1; i < 80; i +=2) {
            testattava.lisaa(i);
            lista.add(i);
        }
        for (int i = 0; i < lista.size(); i++) {
            assertEquals(i + " indeksilla ei ole tallentunut listalle oikein", 
                    lista.get(i), testattava.hae(i));
        }
        assertEquals("getKoko palauttaa väärän listan koon", lista.size(), testattava.getKoko());
        
    }
    
    @Test
    public void ListaJarjestaminenTesti() {
        Lista<Integer> testattava = new Lista<>();
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 20; i > 3; i--) {
            testattava.lisaa(i);
            lista.add(i);
        }
        testattava.jarjesta();
        Collections.sort(lista);
        for (int i = 0; i < lista.size(); i++) {
            assertEquals("lista ei ole oikeassa järjestyksessä", lista.get(i), testattava.hae(i));
        }
    }
}
