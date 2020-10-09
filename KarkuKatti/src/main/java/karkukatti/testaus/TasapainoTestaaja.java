/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.testaus;
import karkukatti.sovelluslogiikka.*;
/**
 *
 * @author salmison
 */
public class TasapainoTestaaja {
    
    public static void main(String[] args) {
        voittoOsuus(11, 40);
        voittoOsuus(13, 40);
//        voittoOsuus(11, 40);
    }
    
    private static void voittoOsuus(int koko, int otos) {
        int montakoPelattu = 0;
        int kissaVoitti = 0;
        for (int i = 0; i < otos; i++) {
            Peli p = new Peli(koko, -1, 7);
            while (!p.getPeliLoppui()) {
                p.tekoalyPelaa();
            }
            montakoPelattu++;
            if (p.getKissaVoitti()) {
                kissaVoitti++;
            }
        }
        int pros = kissaVoitti / montakoPelattu * 100;
        System.out.println(otos + " pelin otoksesta " + koko + "x" + koko +
                "-laudalla kissa voitti " + kissaVoitti);
    }
}
