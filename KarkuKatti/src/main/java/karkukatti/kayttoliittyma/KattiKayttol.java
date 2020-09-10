/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkukatti.kayttoliittyma;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
        
public class KattiKayttol extends Application {
    
    private Scene nakyma;
    private Stage ikkuna;
    
    @Override
    public void init() {
        Label hei = new Label("hei maailma");
        nakyma = new Scene(hei, 500, 500);
    }
    
    @Override
    public void start(Stage primarystage) throws Exception {
        ikkuna = primarystage;
        ikkuna.setTitle("Karkukatti");
        ikkuna.setScene(nakyma);
        ikkuna.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
