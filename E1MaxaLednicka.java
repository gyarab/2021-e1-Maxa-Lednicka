/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2021.e1.maxa.lednicka;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**                     prejmenovat maxsurovin a pocet a nazev
 *
 * @author kryst
 */
public class E1MaxaLednicka extends Application {

    Scanner sc = new Scanner(System.in);        //scanner ktery se bude ptat na jmeno suroviny, ktera se prida do lednicky, a na pocet
    Button[] surovina = new Button[7];          //pole pro tlacitka, ktere predstavuji jednotlive suroviny, 8 mist v lednicce 

    int pocetSurovin = 0;                         //pomocna promena, diky ktere pozname zda muzeme pridat neco do lednicky ci nikoliv, zaroven funguje jako pomoc pro vybrani dalsiho mista v poli
    int[] pocet = new int[7];                   //pole do kterych se vlozi nazvy a pocty jednotlivych produktu
    String[] nazev = new String[7];

    @Override
    public void start(Stage primaryStage) {
        Label L1 = new Label("Napiste prvni jmeno suroviny, pote zmacknete enter. " + "Pak napiste pocet suroviny a zase zmacknete enter.");
        Label L2 = new Label();
        FlowPane pane = new FlowPane(Orientation.HORIZONTAL, 4, 2);

        //tlacitko diky kteremu muzeme pridat surovinu do lednicky
        Button pridatSurovinu = new Button("Pridat surovinu");

        HBox hbox1 = new HBox(pridatSurovinu, L1);                       //hbox pro tlacitko pridat surovinu a popisek
        pane.getChildren().add(hbox1);

        HBox hbox2 = new HBox();                                             //hbox proveci v lednicce 
        pane.getChildren().add(hbox2);

        //HBox hbox3 = new HBox();
        
        pridatSurovinu.setOnMouseClicked((e) -> {
            //kdyz je v lednicce jeste misto, tak si tam muze pridat dalsi vec
            if (pocetSurovin < 8) {

                nazev[pocetSurovin] = sc.next();

                pocet[pocetSurovin] = sc.nextInt();

                surovina[pocetSurovin] = new Button(nazev[pocetSurovin] + " pocet:" + pocet[pocetSurovin]);
                
               /* surovina[pocetSurovin].setOnMouseClicked((a)->{
                    
                  surovina[pocetSurovin].setText(nazev[pocetSurovin] + " pocet:" + (pocet[pocetSurovin]-1));
                });
                */
                
                //  bud to ykusit tak, ze se to ted napise jako kdzy se na to klikne, tak se to udela, a uy si to bude pamatovat
                //  nebo si pod kazde tlacitko vztvorit vlastni odebratSurovinu, ktera tu surovinu odebere
                
                
                
                hbox2.getChildren().add(surovina[pocetSurovin]);
                pocetSurovin++;
               // pane.getChildren().add(hbox2);

                if (pocetSurovin == 7) {
                    L2.setText("V lednicce uz je plno");
                    hbox2.getChildren().add(L1);
                    pane.getChildren().add(hbox2);
                }
            }

        });

         /*surovina[0].setOnMouseClicked((e)->{
            pocet[0]--;
            surovina[0].setText(nazev[0] + " pocet:" + pocet[0]);
        });
         */
        Scene scene = new Scene(pane, 600, 600);

        primaryStage.setTitle("Lednicka");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* public void funkcePridatSurovinu(){
       if (maxSurovin<9){
           Button button
       }
        
    }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
