/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import javax.swing.JSlider;

/**
 *
 * @author jdebat
 */
public class IhmSma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

        MainFraime frame = new MainFraime();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);       
        
        Grille grilleP = frame.getGrille();
        JSlider gestionVitesse = frame.getjSliderVitesse();
        
        int i = 3;
        int iNext = 4;
        /*while (true) {
            grilleP.move(3, Type.ROBOT, Couleur.BLUE, 12, i, 12, iNext);
            i = iNext;
            iNext++;
            if (iNext == 30) {
                iNext = 0;
            }
            int vitesse = Integer.valueOf(gestionVitesse.getValue());
            vitesse ++;
            Thread.sleep(10000/vitesse);
        }*/
        
    }
    
}
