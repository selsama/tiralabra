/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.kayttoliittyma;

import karkukatti.sovelluslogiikka.Peli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    private HBox valikko;
    
    @Override
    public void init() {
        Label hei = new Label("hei maailma");
        nakyma = new Scene(hei, 500, 500);
        this.peli = new Peli(10);
        this.valikko = new HBox(10);
        valikko.setPrefHeight(30);
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
        Label vuoro = new Label("Kissan vuoro");
        valikko.getChildren().addAll(nappi, vuoro);
        VBox alue = new VBox();
        alue.getChildren().addAll(valikko, this.teePelilauta());
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
        return alue;
    }
    
    public void reagoiKlikkaukseen(int x, int y) {
        x -= 5;
        x /= 25;
        y -= 35;
        y /= 25;
        if ( 0 <= x && x < pelilauta.length && 0 <= y && y < pelilauta.length) {
            if( this.peli.reagoiKlikkaukseen(x, y)) {
                pelilauta[x][y].setFill(Color.GREENYELLOW);
            }
        }
        else {
            valikko.getChildren().add(new Label("huti!"+x+y));
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
