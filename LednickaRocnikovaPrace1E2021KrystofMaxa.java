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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author kryst
 */
public class LednickaRocnikovaPrace1E2021KrystofMaxa extends Application {

    Scanner sc = new Scanner(System.in);

    Label mistoVLednicce = new Label("Lednice je plna.");

    Label[] boxLednicky = new Label[7];
    Label[] doplnLednicku = new Label[7];
    Button[] doplnitSurovinu = new Button[7];
    Button[] odebratSurovinu = new Button[7];
    String[] jmenoSuroviny = new String[7];

    int[] pocetSuroviny = new int[7];   //pocet jednotlivych surovin
    int ps = 0;         //popmocna promena pro funkci tlacitka pridatSurovinu
    int suplik = 1;     //pomocna promena pro vypsani poradi jednotlivych supliku

    @Override
    public void start(Stage stage) throws Exception {

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 15, 15);

        //vytvorime tlacitko pridat surovinu, ktere udela to, ze si tam majitel bude moc pridat cokoliv co bude chtit
        Button pridatSurovinu = new Button("Pridat surovinu");
        pridatSurovinu.setMaxSize(200, 200);
        pane.getChildren().add(pridatSurovinu);
        
        //instrukce pro pouziti tlacitka pridatSurovinu
        Label instrukce1 = new Label("Instrukce pro pridani suroviny:");
        Label instrukce2 = new Label("1. Stisknete tlacitko Pridat surovinu.");
        Label instrukce3 = new Label("2. Napiste nazev suroviny bez mezer a stisknete ENTER.");
        Label instrukce4 = new Label("3. Napiste pocet pridane suroviny a stisknete ENTER.");
        pane.getChildren().add(instrukce1);
        pane.getChildren().add(instrukce2);
        pane.getChildren().add(instrukce3);
        pane.getChildren().add(instrukce4);
        //vytvorime vnitrek lednicky
        for (int i = 0; i < 7; i++) {
            //vztvorime novy hbox pro odradkovani
            HBox hbox = new HBox();

            //vytvoreni vnitrku lednicky=pridani mist pro nazev suroviny, pridani tlacitka pridat a odebrat-pro kazdou surovinu, pridani napisu dopln lednicku- kdyz bude treba
            boxLednicky[i] = new Label(suplik + ".Suplik");
            odebratSurovinu[i] = new Button("odebrat");
            doplnitSurovinu[i] = new Button("doplnit");
            doplnLednicku[i] = new Label();

            //vsechno to pridame do hboxu
            hbox.getChildren().add(boxLednicky[i]);
            hbox.getChildren().add(doplnitSurovinu[i]);
            hbox.getChildren().add(odebratSurovinu[i]);
            hbox.getChildren().add(doplnLednicku[i]);
            //hbox pridame do paneu
            pane.getChildren().add(hbox);
            suplik++;
        }

        //kdyz zmackne pridatSurovinu, pojmenuje si prvni label pomoci scanneru
        //pricte se 1, aby kdyz klikne na tlacitko znovu, mohl pojmenovat dalsi box lednicky
        pridatSurovinu.setOnAction((pS) -> {
            //udelat nejakz if, ze kdyz bude plno tak to napise
            if (ps < 8) {
                jmenoSuroviny[ps] = sc.next();       //zepta se na jmeno suroviny
                pocetSuroviny[ps] = sc.nextInt();    //zepta se na pocet suroviny

                boxLednicky[ps].setText(jmenoSuroviny[ps] + " pocet:" + pocetSuroviny[ps]);  //prepise text
                ps++;    //pricteme 1, aby mohl pojmenovavat dalsi boxLednicky
            } else {
                pane.getChildren().add(mistoVLednicce);
            }
        });

        //naprogramujeme si vsechna tlacitka
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
            pocetSuroviny[0]++;
            boxLednicky[0].setText(jmenoSuroviny[0] + " pocet:" + pocetSuroviny[0]);
            doplnLednicku[0].setText("");
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

        doplnitSurovinu[1].setOnAction((z2) -> {
            pocetSuroviny[1]++;
            boxLednicky[1].setText(jmenoSuroviny[1] + " pocet:" + pocetSuroviny[1]);
            doplnLednicku[1].setText("");
        });

        //treti radek
        odebratSurovinu[2].setOnAction((z1) -> {
            if (pocetSuroviny[2] > 0) {
                pocetSuroviny[2]--;
                boxLednicky[2].setText(jmenoSuroviny[2] + " pocet:" + pocetSuroviny[2]);

                if (pocetSuroviny[2] <= 0) {
                    doplnLednicku[2].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[2].setOnAction((z2) -> {
            pocetSuroviny[2]++;
            boxLednicky[2].setText(jmenoSuroviny[2] + " pocet:" + pocetSuroviny[2]);
            doplnLednicku[2].setText("");
        });

        //ctvrty radek
        odebratSurovinu[3].setOnAction((z1) -> {
            if (pocetSuroviny[3] > 0) {
                pocetSuroviny[3]--;
                boxLednicky[3].setText(jmenoSuroviny[3] + " pocet:" + pocetSuroviny[3]);

                if (pocetSuroviny[3] <= 0) {
                    doplnLednicku[3].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[3].setOnAction((z2) -> {
            pocetSuroviny[3]++;
            boxLednicky[3].setText(jmenoSuroviny[3] + " pocet:" + pocetSuroviny[3]);
            doplnLednicku[3].setText("");
        });

        //paty radek
        odebratSurovinu[4].setOnAction((z1) -> {
            if (pocetSuroviny[4] > 0) {
                pocetSuroviny[4]--;
                boxLednicky[4].setText(jmenoSuroviny[4] + " pocet:" + pocetSuroviny[4]);

                if (pocetSuroviny[4] <= 0) {
                    doplnLednicku[4].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[4].setOnAction((z2) -> {
            pocetSuroviny[4]++;
            boxLednicky[4].setText(jmenoSuroviny[4] + " pocet:" + pocetSuroviny[4]);
            doplnLednicku[4].setText("");
        });

        //sesty radek
        odebratSurovinu[5].setOnAction((z1) -> {
            if (pocetSuroviny[5] > 0) {
                pocetSuroviny[5]--;
                boxLednicky[5].setText(jmenoSuroviny[5] + " pocet:" + pocetSuroviny[5]);

                if (pocetSuroviny[5] <= 0) {
                    doplnLednicku[5].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[5].setOnAction((z2) -> {
            pocetSuroviny[5]++;
            boxLednicky[5].setText(jmenoSuroviny[5] + " pocet:" + pocetSuroviny[5]);
            doplnLednicku[5].setText("");
        });

        //sedmy radek 
        odebratSurovinu[6].setOnAction((z1) -> {
            if (pocetSuroviny[6] > 0) {
                pocetSuroviny[6]--;
                boxLednicky[6].setText(jmenoSuroviny[6] + " pocet:" + pocetSuroviny[6]);

                if (pocetSuroviny[6] <= 0) {
                    doplnLednicku[6].setText("DOPLN JIDLO!!!");
                }
            }
        });

        doplnitSurovinu[6].setOnAction((z2) -> {
            pocetSuroviny[6]++;
            boxLednicky[6].setText(jmenoSuroviny[6] + " pocet:" + pocetSuroviny[6]);
            doplnLednicku[6].setText("");
        });
        /* 
          odebratSurovinu[7].setOnAction((z15)->{
             pocetSuroviny[7]--;
             boxLednicky[7].setText(jmenoSuroviny[7] + " pocet:" + pocetSuroviny[7]);
         });
         
        doplnitSurovinu[7].setOnAction((z16)->{
             pocetSuroviny[7]++;
             boxLednicky[7].setText(jmenoSuroviny[7] + " pocet:" + pocetSuroviny[7]);
         });
         
         
         */

        Scene scene = new Scene(pane, 700, 700);

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
