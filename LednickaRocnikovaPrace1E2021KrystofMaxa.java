/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lednickarocnikovaprace1e2021krystofmaxa;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author kryst
 */
public class LednickaRocnikovaPrace1E2021KrystofMaxa extends Application {

    Label[] boxLednicky = new Label[7];             //pole pro misto na vlozeni jmena surovin
    Label[] doplnLednicku = new Label[7];           //pole pro napisu dopl lednicku, ke kazdemu radku
    Button[] doplnitSurovinu = new Button[7];       //pole pro tlacitka doplnSurovinu
    Button[] odebratSurovinu = new Button[7];       //pole pro tlacitka odeberSurovinu
    String[] jmenoSuroviny = new String[7];         //pole pro jmena surovin

    int[] pocetSuroviny = new int[7];   //pocet jednotlivych surovin
    int ps = 0;                         //popmocna promena pro funkci tlacitka pridatSurovinu
    int suplik = 1;                     //pomocna promena pro vypsani poradi jednotlivych supliku
    boolean[] boxPrazdny = new boolean[7];             //kdyz je boxPrazdny, tlacitko doplnitSurovinu nic neprida
    @Override
    public void start(Stage stage) throws Exception {

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 15, 15);
        VBox hlavniVBox1 = new VBox();           //hlavni vbox do ktereho budeme pridavat vsechny objekty
        hlavniVBox1.setAlignment(Pos.CENTER);    //pro vycentrovani 
        pane.getChildren().addAll(hlavniVBox1);  //hlavniVBox pridame do paneu

        //instrukce pro pouziti tlacitka pridatSurovinu
        Label instrukce1 = new Label("Instrukce pro pridani suroviny:");
        instrukce1.setFont(Font.font("Cambria", 20));
        
        Label mezera = new Label();
        Label instrukce2 = new Label("1. Do prvniho pole napiste jmeno suroviny.");
        Label instrukce3 = new Label("2. Do druheho pole napiste pocet suroviny.");
        Label instrukce4 = new Label("2.1. Pro vlozeni poctu pouzijte jen cislice.");
        hlavniVBox1.getChildren().addAll(instrukce1, mezera, instrukce2, instrukce3, instrukce4);

        //vytvorime si textova pole pro zadani jmena a pocty surovin 
        TextField textJmeno = new TextField();
        TextField textPocet = new TextField();
        textJmeno.setPromptText("Zadejte surovinu:");
        textPocet.setPromptText("Zadejte pocet suroviny:");
        hlavniVBox1.getChildren().addAll(textJmeno, textPocet);

        //vytvorime tlacitko pridat surovinu, ktere prida to, co se napise do textoveho pole
        Button pridatSurovinu = new Button("Pridat surovinu");
        pridatSurovinu.setMaxSize(200, 200);
        hlavniVBox1.getChildren().add(pridatSurovinu);

        //vytvorime vnitrek lednicky, ktery nasledne budeme pojmenovavat pomoci tlacitka pridatSurovinu
        for (int i = 0; i < 7; i++) {
            //vytvorime novy hbox pro odradkovani
            HBox hbox = new HBox();

            //vytvoreni vnitrku lednicky:
            //pridani mista(Label) pro nazev suroviny 
            //pridani tlacitka pridat a odebrat - pro kazdou surovinu
            //pridani napisu dopln lednicku - kdyz bude treba
            boxLednicky[i] = new Label(suplik + ".Suplik");
            odebratSurovinu[i] = new Button("odebrat");
            String pozadiOdebrat =  "-fx-background-color: #FF3535;" +
                                    "-fx-border-color: black;" +
                                    "-fx-border-width: 2;";   
            odebratSurovinu[i].setStyle(pozadiOdebrat);
            
            doplnitSurovinu[i] = new Button("doplnit");
            String pozadiDoplnit =  "-fx-background-color: #90ee90;" +
                                    "-fx-border-color: black;" +
                                    "-fx-border-width: 2;";
            doplnitSurovinu[i].setStyle(pozadiDoplnit);
            
            doplnLednicku[i] = new Label();
            
            boxPrazdny[i] = true;
            //vsechno to pridame do hboxu
            hbox.getChildren().addAll(boxLednicky[i], doplnitSurovinu[i], odebratSurovinu[i], doplnLednicku[i]);

            //hbox pridame do hlavnihoVBoxu
            hlavniVBox1.getChildren().add(hbox);
            //abychom nemuseli psat 1.suplik, 2.suplik... pomuzeme si promenou suplik, ke ktere vzdy pricteme jednicku 
            suplik++;
        }

        //vytvorime si label, ktery se ukaze kdyz bude lednice plna
        Label mistoVLednicce = new Label();
        hlavniVBox1.getChildren().add(mistoVLednicce);
        
        //kdyz zmackne tlacitko pridatSurovinu, pojmenuje si urcity label pomoci textoveho pole
        pridatSurovinu.setOnAction((pS) -> {
            //udelat if, ze kdyz bude plno, tak to napise: neni misto v lednicce 
            if (ps < 7) {
                jmenoSuroviny[ps] = textJmeno.getText();                      //zepta se na jmeno suroviny
                pocetSuroviny[ps] = Integer.parseInt(textPocet.getText());    //zepta se na pocet suroviny

                boxLednicky[ps].setText(jmenoSuroviny[ps] + " pocet:" + pocetSuroviny[ps]);  //prepise text v supliku
                
                doplnLednicku[ps].setText("");          //v pripade ze by bylo napsane v doplLednicku("v supliku nic neni"), tak to zmizi, protoze jsme tam uz neco pridali
                boxPrazdny[ps] = false;
                ps++;    //pricteme 1, aby mohl pojmenovavat dalsi boxLednicky
            } else {
                mistoVLednicce.setText("Lednice je plna!");    //kdyz neni misto, napise to
                mistoVLednicce.setFont(Font.font("Cambria", 18));
            }
        });

   
     //tato cast mi nesla udelat pomoci forcyklu, tak jsem ji musel celou vypsat   
        //prvni radek
        odebratSurovinu[0].setOnAction((z1) -> {
            if (pocetSuroviny[0] > 0) {
                pocetSuroviny[0]--;
                boxLednicky[0].setText(jmenoSuroviny[0] + " pocet:" + pocetSuroviny[0]);

                if (pocetSuroviny[0] <= 0) {
                    doplnLednicku[0].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[0].setOnAction((z2) -> {
           //v pripade ze jsme jeste nic nedoplnili do urciteho supliku, program nam napise, ze v supliku nic neni
           //pro vsechny ostatni tlacitka doplnitSurovinu plati totez
             if(boxPrazdny[0]==true){
                doplnLednicku[0].setText("V supliku nic neni.");
           }else{
                pocetSuroviny[0]++;
                boxLednicky[0].setText(jmenoSuroviny[0] + " pocet:" + pocetSuroviny[0]);
                doplnLednicku[0].setText("");
           }
        });

        //druhy radek
        odebratSurovinu[1].setOnAction((z3) -> {
            if (pocetSuroviny[1] > 0) {
                pocetSuroviny[1]--;
                boxLednicky[1].setText(jmenoSuroviny[1] + " pocet:" + pocetSuroviny[1]);

                if (pocetSuroviny[1] <= 0) {
                    doplnLednicku[1].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[1].setOnAction((z4) -> {
            if (boxPrazdny[1]==true){
               doplnLednicku[1].setText("V supliku nic neni.");
           } else{
            pocetSuroviny[1]++;
            boxLednicky[1].setText(jmenoSuroviny[1] + " pocet:" + pocetSuroviny[1]);
            doplnLednicku[1].setText("");
            }
        });

        //treti radek
        odebratSurovinu[2].setOnAction((z5) -> {
            if (pocetSuroviny[2] > 0) {
                pocetSuroviny[2]--;
                boxLednicky[2].setText(jmenoSuroviny[2] + " pocet:" + pocetSuroviny[2]);

                if (pocetSuroviny[2] <= 0) {
                    doplnLednicku[2].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[2].setOnAction((z6) -> {
            if (boxPrazdny[2]==true){
                doplnLednicku[2].setText("V supliku nic neni.");
           } else{
            pocetSuroviny[2]++;
            boxLednicky[2].setText(jmenoSuroviny[2] + " pocet:" + pocetSuroviny[2]);
            doplnLednicku[2].setText("");
            }
        });

        //ctvrty radek
        odebratSurovinu[3].setOnAction((z7) -> {
            if (pocetSuroviny[3] > 0) {
                pocetSuroviny[3]--;
                boxLednicky[3].setText(jmenoSuroviny[3] + " pocet:" + pocetSuroviny[3]);

                if (pocetSuroviny[3] <= 0) {
                    doplnLednicku[3].setText("DOPLN JIDLO!!!");
                }
            }
        });
        
        doplnitSurovinu[3].setOnAction((z8) -> {
            if (boxPrazdny[3]==true){
                doplnLednicku[3].setText("V supliku nic neni.");
           } else{
            pocetSuroviny[3]++;
            boxLednicky[3].setText(jmenoSuroviny[3] + " pocet:" + pocetSuroviny[3]);
            doplnLednicku[3].setText("");
            }
        });

        //paty radek
        odebratSurovinu[4].setOnAction((z9) -> {
            if (pocetSuroviny[4] > 0) {
                pocetSuroviny[4]--;
                boxLednicky[4].setText(jmenoSuroviny[4] + " pocet:" + pocetSuroviny[4]);

                if (pocetSuroviny[4] <= 0) {
                    doplnLednicku[4].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[4].setOnAction((z10) -> {
            if (boxPrazdny[4]==true){
                doplnLednicku[4].setText("V supliku nic neni.");
           } else{
            pocetSuroviny[4]++;
            boxLednicky[4].setText(jmenoSuroviny[4] + " pocet:" + pocetSuroviny[4]);
            doplnLednicku[4].setText("");
            }
        });

        //sesty radek
        odebratSurovinu[5].setOnAction((z11) -> {
            if (pocetSuroviny[5] > 0) {
                pocetSuroviny[5]--;
                boxLednicky[5].setText(jmenoSuroviny[5] + " pocet:" + pocetSuroviny[5]);

                if (pocetSuroviny[5] <= 0) {
                    doplnLednicku[5].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[5].setOnAction((z12) -> {
            if (boxPrazdny[5]==true){
               doplnLednicku[5].setText("V supliku nic neni.");
           } else{
            pocetSuroviny[5]++;
            boxLednicky[5].setText(jmenoSuroviny[5] + " pocet:" + pocetSuroviny[5]);
            doplnLednicku[5].setText("");
            }
        });

        //sedmy radek 
        odebratSurovinu[6].setOnAction((z13) -> {
            if (pocetSuroviny[6] > 0) {
                pocetSuroviny[6]--;
                boxLednicky[6].setText(jmenoSuroviny[6] + " pocet:" + pocetSuroviny[6]);

                if (pocetSuroviny[6] <= 0) {
                    doplnLednicku[6].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[6].setOnAction((z14) -> {
            if(boxPrazdny[6]==true){
                doplnLednicku[6].setText("V supliku nic neni.");
           }else{
                pocetSuroviny[6]++;
                boxLednicky[6].setText(jmenoSuroviny[6] + " pocet:" + pocetSuroviny[6]);
                doplnLednicku[6].setText("");
           }        });

        Scene scene = new Scene(pane, 550, 450);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
