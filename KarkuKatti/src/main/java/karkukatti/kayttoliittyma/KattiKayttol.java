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
import javafx.stage.Stage;
        
public class KattiKayttol extends Application {
    
    private Scene nakyma;
    private Scene pelinakyma;
    private Stage ikkuna;
    private Peli peli;
    private Rectangle[][] pelilauta;
    private HBox ylavalikko;
    private Label vuoro;
    
    @Override
    public void init() {
        Label hei = new Label("hei maailma");
        nakyma = new Scene(hei, 500, 500);
        this.peli = new Peli(10);
        this.ylavalikko = new HBox(10);
        ylavalikko.setPrefHeight(30);
    }
    
    @Override
    public void start(Stage primarystage) throws Exception {
        ikkuna = primarystage;
        ikkuna.setTitle("Karkukatti");
        this.teePelinakyma();
        ikkuna.setScene(pelinakyma);
        ikkuna.show();
        
    }
    
    public void teePelinakyma() {
        Button nappi = new Button("Nappi!");
        vuoro = new Label("Pelaajan vuoro");
        ylavalikko.getChildren().addAll(nappi, vuoro);
        VBox alue = new VBox();
        alue.getChildren().addAll(ylavalikko, this.teePelilauta());
        pelinakyma = new Scene(alue, 500, 500);
        pelinakyma.setOnMousePressed(e -> {
            this.reagoiKlikkaukseen((int) e.getSceneX(), (int) e.getSceneY());
        });
    }
    
    public Pane teePelilauta() {
        int koko = this.peli.getKoko();
        this.pelilauta = new Rectangle[koko][koko];
        Pane alue = new BorderPane();
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                pelilauta[i][j] = new Rectangle(5+25*i, 5+25*j, 20, 20);
                pelilauta[i][j].setFill(Color.BLUEVIOLET);
                alue.getChildren().add(pelilauta[i][j]);
            }
        }
        pelilauta[peli.getKissanSijainti().getX()][peli.getKissanSijainti().getY()].setFill(Color.BROWN);
        return alue;
    }
    
    public void reagoiKlikkaukseen(int x, int y) {
        x -= 5;
        x /= 25;
        y -= 35;
        y /= 25;
        if ( 0 <= x && x < pelilauta.length && 0 <= y && y < pelilauta.length) {
            Sijainti s = peli.getKissanSijainti();
            boolean kissanVuoro = peli.getKissanVuoro();
            if (this.peli.reagoiKlikkaukseen(x, y)) {
                if (kissanVuoro) {
                    pelilauta[s.getX()][s.getY()].setFill(Color.BLUEVIOLET);
                    pelilauta[x][y].setFill(Color.BROWN);
                    vuoro.setText("Pelaajan vuoro");
                }
                else {
                    pelilauta[x][y].setFill(Color.GREENYELLOW);
                    vuoro.setText("Kissan vuoro");
                }
            }
            else {
                vuoro.setText("Siirto ei sallittu. " + vuoro.getText());
            }
        }
        else {
            vuoro.setText("Klikkasit ohi laudasta. " + vuoro.getText());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
