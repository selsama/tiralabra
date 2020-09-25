/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.kayttoliittyma;

import karkukatti.sovelluslogiikka.Peli;
import karkukatti.sovelluslogiikka.Sijainti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
        
public class KattiKayttol extends Application {
    
    private Scene pelinakyma;
    private Scene asetusnakyma;
    private Scene peliohinakyma;
    private Stage ikkuna;
    private Peli peli;
    private Rectangle[][] pelilauta;
    private HBox ylavalikko;
    private Label vuoro;
    private Label ilmoitus;
    private Button uusiPeliNappi;
    private Button asetuksetNappi;
    private Pane pelialue;
    private int pelaajat;

    @Override
    public void init() {
        this.pelaajat = 3;
        this.peli = new Peli(10, pelaajat);
        this.vuoro = new Label();
        this.ilmoitus = new Label();
        ilmoitus.setTextFill(Color.RED);
        this.uusiPeliNappi = new Button("Uusi peli");
        uusiPeliNappi.setOnAction(e -> {
            this.peli = new Peli(this.peli.getKoko(), pelaajat);
            this.teePelinakyma();
            this.ikkuna.setScene(pelinakyma);
        });
        asetuksetNappi = new Button("Asetukset");
        asetuksetNappi.setOnAction(e -> {
            this.teeAsetusnakyma();
            this.ikkuna.setScene(asetusnakyma);
        });
        this.ylavalikko = new HBox(10);
        ylavalikko.setPrefHeight(30);
        ylavalikko.getChildren().addAll(uusiPeliNappi, asetuksetNappi, vuoro, ilmoitus);
        this.pelialue = new Pane();
    }
    
    @Override
    public void start(Stage primarystage) throws Exception {
        ikkuna = primarystage;
        ikkuna.setTitle("Karkukatti");
        this.teePelinakyma();       
        ikkuna.setScene(pelinakyma);
        ikkuna.show();
        
    }
    
    /**
     * Tekee pelinakyma-scenen.
     */
    public void teePelinakyma() {
        pelialue = this.teePelilauta();
        VBox kaikki = new VBox();
        kaikki.getChildren().addAll(ylavalikko, pelialue);
        pelinakyma = new Scene(kaikki, 500, 500);
        pelinakyma.setOnMousePressed(e -> {
            this.reagoiKlikkaukseen((int) e.getSceneX(), (int) e.getSceneY());
        });
        vuoro.setText("Pelaaja aloittaa");
    }
    
    /**
     * Tekee ja palauttaa pelilaudan, joka muodostuu neliöistä.
     * @return luotu pelilauta
     */
    public Pane teePelilauta() {
        int koko = this.peli.getKoko();
        this.pelilauta = new Rectangle[koko][koko];
        Pane alue = new BorderPane();
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                pelilauta[i][j] = new Rectangle(5 + (25 * i), 5 + (25 * j), 20, 20);
                pelilauta[i][j].setFill(Color.BLUEVIOLET);
                alue.getChildren().add(pelilauta[i][j]);
            }
        }
        pelilauta[peli.getKissanSijainti().getX()][peli.getKissanSijainti().getY()].setFill(Color.BROWN);
        return alue;
    }
    
    /**
     * Tekee asetusnäkymän.
     */
    public void teeAsetusnakyma() {
        HBox asetukset = new HBox();
        Button kaksiPelaajaa = new Button("Kaksi pelaajaa");
        kaksiPelaajaa.setOnAction(e -> {
            this.pelaajat = 3;
        });
        Button pelaaSeinilla = new Button("Pelaa kissaa vastaan");
        pelaaSeinilla.setOnAction(e -> {
            this.pelaajat = 0;
        });
        Button pelaaKissalla = new Button("Pelaa kissalla");
        pelaaKissalla.setOnAction(e -> {
            this.pelaajat = 1;
        });
        asetukset.getChildren().addAll(kaksiPelaajaa, pelaaSeinilla, pelaaKissalla);
        VBox kaikki = new VBox();
        kaikki.getChildren().addAll(ylavalikko, asetukset);
        asetusnakyma = new Scene(kaikki, 500, 500);
    }
    
    public void teePeliOhiNakyma(String teksti) {
        Label peliLoppu = new Label("Peli loppui!");
        Label kumpiVoitti = new Label(teksti);
        kumpiVoitti.setFont(new Font(24));
        Label viesti = new Label("Aloita uusi peli ylävalikosta tai siirry asetuksiin");
        VBox kaikki = new VBox();
        kaikki.getChildren().addAll(ylavalikko, peliLoppu, kumpiVoitti, viesti);
        peliohinakyma = new Scene(kaikki, 500, 500);
    }
    
    /**
     * Käsittelee hiiren klikkauksen. Kutsuu peliä asiaankuuluvasti. Tarkistaa että klikkaus sijoittuu pelilaudalle.
     * @param x Klikatun kohdan x-koordinaatti
     * @param y Klikatun kohdan y-koordinaatti
     */
    public void reagoiKlikkaukseen(int x, int y) {
        x -= 5;
        x /= 25;
        y -= 35;
        y /= 25;
        if (0 <= x && x < pelilauta.length && 0 <= y && y < pelilauta.length) {
            Sijainti missaKissaOli = peli.getKissanSijainti();
            Sijainti uusi = new Sijainti(x, y);
            boolean kissanVuoro = peli.getKissanVuoro();
            if (this.peli.reagoiKlikkaukseen(x, y)) {
                if (kissanVuoro) {
                    this.siirraKissaa(uusi, missaKissaOli);
                    vuoro.setText("Pelaajan vuoro");
                } else {
                    this.teeSeinaruutu(uusi);
                    vuoro.setText("Kissan vuoro");
                }
                ilmoitus.setText("");
                missaKissaOli = peli.getKissanSijainti();
                kissanVuoro = peli.getKissanVuoro();
                Sijainti tekoalynSiirto = peli.tekoalyPelaa();
                if (tekoalynSiirto != null) {
                    if (kissanVuoro) {
                        this.siirraKissaa(tekoalynSiirto, missaKissaOli);
                    } else {
                        this.teeSeinaruutu(tekoalynSiirto);
                    }
                }
                
            } else {
                ilmoitus.setText("Siirto ei sallittu.");
            }
        } else {
            ilmoitus.setText("Klikkasit ohi laudasta.");
        }
        if (peli.getPeliLoppui()) {
            if (peli.getKissaVoitti()) {
                this.teePeliOhiNakyma("Kissa voitti!");
            } else {
                this.teePeliOhiNakyma("Seinäpelaaja voitti!");
            }
            this.ikkuna.setScene(peliohinakyma);
        }
    }
    
    private void siirraKissaa(Sijainti uusi, Sijainti kissanVanha) {
        pelilauta[uusi.getX()][uusi.getY()].setFill(Color.BROWN);
        pelilauta[kissanVanha.getX()][kissanVanha.getY()].setFill(Color.BLUEVIOLET);
        vuoro.setText("Seinäpelaajan vuoro");
    }
    
    private void teeSeinaruutu(Sijainti s) {
        pelilauta[s.getX()][s.getY()].setFill(Color.GREENYELLOW);
        vuoro.setText("Kissan vuoro");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
