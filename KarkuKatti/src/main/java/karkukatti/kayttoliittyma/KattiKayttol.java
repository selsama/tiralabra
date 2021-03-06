/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.kayttoliittyma;

import karkukatti.sovelluslogiikka.Peli;
import karkukatti.sovelluslogiikka.apuluokkia.Sijainti;
import karkukatti.sovelluslogiikka.apuluokkia.Lista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
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
    private int seinienMaaraAlussa;
    private int pelilaudanKoko;
    private Paint kissa;
    private Paint kissaLaatikossa;

    @Override
    public void init() {
        this.teeKuvat();
        this.pelaajat = 3;
        this.seinienMaaraAlussa = 5;
        this.pelilaudanKoko = 11;
        this.peli = new Peli(pelilaudanKoko, pelaajat, seinienMaaraAlussa);
        this.vuoro = new Label();
        this.ilmoitus = new Label();
        ilmoitus.setTextFill(Color.RED);
        this.uusiPeliNappi = new Button("Uusi peli");
        uusiPeliNappi.setOnAction(e -> {
            this.peli = new Peli(this.peli.getKoko(), pelaajat, seinienMaaraAlussa);
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
        String teksti = peli.getKissanVuoro() ? "Kissa aloittaa" : "Seinäpelaaja aloittaa";
        vuoro.setText(teksti);
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
        pelilauta[peli.getKissanSijainti().getX()][peli.getKissanSijainti().getY()].setFill(this.kissa);
        Lista<Sijainti> seinat = peli.getAlkuseinat();
        for (int i = 0; i < seinat.getKoko(); i++) {
            pelilauta[seinat.hae(i).getX()][seinat.hae(i).getY()].setFill(Color.GREENYELLOW);
        }
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
        HBox seinienMaara = new HBox();
        Label ohje = new Label("Muuta alussa luotavien seinien määrää:");
        TextField seinat = new TextField();
        Button muuta = new Button("Muuta");
        Label virhe = new Label();
        virhe.setTextFill(Color.RED);
        muuta.setOnAction(e -> {
            String s = seinat.getText();
            try {
                int a = Integer.parseInt(s);
                if (a < 0) {
                    virhe.setText("Luvun pitää olla positiivinen");
                } else if (a > 2 * pelilaudanKoko) {
                    virhe.setText("Luvun pitää olla pienempi kuin " + (2 * pelilaudanKoko));
                } else {
                    seinienMaaraAlussa = a;
                    virhe.setText("");
                }
            } catch (Exception ex) {
                virhe.setText("Ole hyvä ja syötä kokonaisluku numeroina");
            }
        });
        seinienMaara.getChildren().addAll(ohje, seinat, muuta, virhe);
        asetukset.getChildren().addAll(kaksiPelaajaa, pelaaSeinilla, pelaaKissalla);
        VBox kaikki = new VBox();
        kaikki.getChildren().addAll(ylavalikko, asetukset, seinienMaara);
        asetusnakyma = new Scene(kaikki, 500, 500);
    }
    
    public void teePeliOhiNakyma(String teksti) {
        Label peliLoppu = new Label("Peli loppui!");
        Label kumpiVoitti = new Label(teksti);
        kumpiVoitti.setFont(new Font(24));
        Label viesti = new Label("Aloita uusi peli ylävalikosta tai siirry asetuksiin");
        VBox kaikki = new VBox();
        Rectangle voittaja = new Rectangle(400, 400);
        voittaja.setFill(teksti.equals("Kissa voitti!") ? kissa : kissaLaatikossa);
        kaikki.getChildren().addAll(ylavalikko, peliLoppu, kumpiVoitti, viesti, voittaja);
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
                    vuoro.setText("Seinäpelaajan vuoro");
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
        pelilauta[uusi.getX()][uusi.getY()].setFill(this.kissa);
        pelilauta[kissanVanha.getX()][kissanVanha.getY()].setFill(Color.BLUEVIOLET);
        vuoro.setText("Seinäpelaajan vuoro");
    }
    
    private void teeSeinaruutu(Sijainti s) {
        pelilauta[s.getX()][s.getY()].setFill(Color.GREENYELLOW);
        vuoro.setText("Kissan vuoro");
    }
    
    private void teeKuvat() {
        try {
            this.kissa = new ImagePattern(new Image("file:katti.png"));
            this.kissaLaatikossa = new ImagePattern(new Image("file:seina.png"));
        } catch (Exception e) {
            this.kissa = Color.BROWN;
            this.kissaLaatikossa = Color.GREENYELLOW;
            System.out.println("jos haluat nähdä kuvia, tarkista että tiedostot katti.png ja seina.png ovat oikeassa paikassa" + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
