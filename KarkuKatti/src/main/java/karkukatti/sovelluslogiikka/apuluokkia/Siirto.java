/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.sovelluslogiikka.apuluokkia;

/**
 *Luokka mahdollisten siirtojen tallentamiseen. Pitää sisällään tiedon siirron kohteesta ja siitä, kuinka hyvä siirto on.
 */
public class Siirto {
    private Sijainti kohde;
    private double hyvyys;
//    private int reittienMaara;
//    private int lyhinReitti;
//    private double reittienKeskipituus;
    
    public Siirto(Sijainti kohde, double hyvyys) {
        this.kohde = kohde;
        this.hyvyys = hyvyys;
    }
    
    public Sijainti getKohde() {
        return kohde;
    }
    
    public double getHyvyys() {
        return hyvyys;
    }
    
//    public Siirto parempiSiirto(Siirto toinen) {
//        int maaraEro = reittienMaara - toinen.getReittienMaara();
//        //halutaan että kaikki arvot jäävät positiivisiksi, kun tämä on parempi kuin toinen
//        int lyhimmanEro = toinen.getLyhinReitti() - lyhinReitti;
//        double kaEro = toinen.getReittienKeskipituus() - reittienKeskipituus;
//        double yhteensaEro = maaraEro + lyhimmanEro + kaEro;
//        int montakoVoittaa = 0;
//        if (maaraEro > 0) {
//            montakoVoittaa++;
//        } else if (maaraEro < 0) {
//            montakoVoittaa--;
//        }
//        if (lyhimmanEro > 0) {
//            montakoVoittaa++;
//        } else if (lyhimmanEro < 0) {
//            montakoVoittaa--;
//        }
//        if (kaEro > 0) {
//            montakoVoittaa++;
//        } else if (kaEro < 0) {
//            montakoVoittaa--;
//        }
//        
//        if (montakoVoittaa == 3) {
//            return this; // jos voittaa kaikessa, tietenkin parempi
//        } else if (montakoVoittaa == -3) {
//            return toinen; //jos häviää kaikessa, tietenkin huonompi
//        } else if (yhteensaEro > 5) {
//            return this; // jos ero selvä, parempi
//        } else if (yhteensaEro < -5) {
//            return toinen; // jos ero selvä, huonompi
//        } else if (montakoVoittaa == 2) {
//            return this;
//        } else if (montakoVoittaa == -2) {
//            return toinen;
//        } else if (reittienMaara != 0) {
//            if (reittienMaara > 0) {
//                return this;
//            }
//            return toinen;
//        } else if (lyhimmanEro != 0) {
//            if (lyhimmanEro > 0) {
//                return this;
//            }
//            return toinen;
//        } else {
//            if (kaEro > 0) {
//                return this;
//            }
//            return toinen;
//        }
//    }
//    
//    public int getReittienMaara() {
//        return reittienMaara;
//    }
//    
//    public int getLyhinReitti() {
//        return lyhinReitti;
//    }
//    
//    public double getReittienKeskipituus() {
//        return reittienKeskipituus;
//    }
    
    
    
}
