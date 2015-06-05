/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import com.sun.org.apache.bcel.internal.util.SecuritySupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ComboBoxEditor;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import sun.applet.Main;

/**
 *
 * @author Johan
 */
public class Grille extends javax.swing.JPanel {
    public int nbLigne;
    public int nbColonne;
    private Dimension dimensionCase;
    public static Case casePanelTable[][];
    private MainFraime frame;
    
    final String red = "red";
    final String green = "green";
    final String blue = "blue";
    
    public Color greenColor = new Color(188, 255, 181);
    public Color redColor = new Color(255, 181, 181);
    public Color blueColor = new Color(181, 255, 255);
    
    
    /**
     * Creates new form Grille
     */
    public Grille(int lignes, int colonnes, MainFraime pere) {
        initComponents();
        nbLigne = lignes;
        nbColonne = colonnes;
        frame = pere;
        dimensionCase = calculTailleCase();
        
        setLayout(new GridLayout(lignes, colonnes, 0, 0));
        casePanelTable = new Case[nbLigne][nbColonne];
        for(int colonne = 0; colonne < nbColonne; colonne ++) {
            for(int ligne = 0; ligne < nbLigne; ligne ++) {
                casePanelTable[ligne][colonne] = new Case(dimensionCase, ligne, colonne, this);
                add(casePanelTable[ligne][colonne]);
            }
        }
        
        //Ajout des nids
        /*addComposant(casePanelTable[6][7], 1, Type.NID, Couleur.BLUE);
        addComposant(casePanelTable[6][14], 2, Type.NID, Couleur.RED);
        addComposant(casePanelTable[6][21], 3, Type.NID, Couleur.GREEN);
        
        //addComposant(casePanelTable[6][21], 4, Type.ROBOT, Couleur.GREEN);
        addComposant(casePanelTable[21][21], 1, Type.ROBOT, Couleur.GREEN);
        addComposant(casePanelTable[10][13], 2, Type.ROBOT, Couleur.RED);
        addComposant(casePanelTable[12][3], 3, Type.ROBOT, Couleur.BLUE);
        
        addComposant(casePanelTable[10][13], 1, Type.BOITE, Couleur.GREEN);
        addComposant(casePanelTable[15][26], 2, Type.BOITE, Couleur.RED);
        addComposant(casePanelTable[21][21], 3, Type.BOITE, Couleur.BLUE);
        
        addComposant(casePanelTable[0][0], 1, Type.BOITE, Couleur.GREEN);
        addComposant(casePanelTable[0][0], 2, Type.ROBOT, Couleur.RED);
        removeComposant(casePanelTable[0][0], 2, Type.ROBOT, Couleur.RED);*/
        
  
        setVisible(true);

    }
    private Dimension calculTailleCase() {
        return new Dimension(this.getWidth()/nbLigne, this.getHeight()/nbColonne);
    }

    public void addComposant(Case c, int id, Type type, Couleur couleur) {
        Composant composantToAdd = new Composant(id, type, couleur);
        c.addComposant(composantToAdd);
    }
    
    public void removeComposant(Case c, int id, Type type, Couleur couleur) {
        Composant composantToRemove = new Composant(id, type, couleur);
        c.removeComposant(composantToRemove);
    }

    public static Case[][] getCasePanelTable() {
        return casePanelTable;
    }
    
    
    
    public void updateInfos(int ligne, int colonne) {
        frame.getjLabelPositionX().setText(String.valueOf(ligne));
        frame.getjLabelPositionY().setText(String.valueOf(colonne));
        
        String infos = "<html>";
        Case c = casePanelTable[ligne][colonne];
        System.out.println(c.toString());
        for (Composant composant : c.listComposants) {
            System.out.println(composant.toString());
            infos += composant.toString();
        }
        infos+="</html>";
        frame.getjLabelComposantsCase().setText(infos);
    }
    
    public void move(int idComposant, Type type, Couleur couleur, int oldAbs, int oldOrd, int abs, int ord) {
        System.out.println("DEBUT");
        Case cOld = casePanelTable[oldAbs][oldOrd];
        cOld.removeAll();
        System.out.println(cOld.toString());
        Case cNew = casePanelTable[abs][ord];
        System.out.println(cNew.toString());
        Composant toMove = new Composant(idComposant, type, couleur);
        
        removeComposant(cOld, idComposant, type, couleur);
        addComposant(cNew, idComposant, type, couleur);
        cOld.removeComposant(toMove);
        //cNew.addComposant(toMove);
        validate();
    }
    
    public void clearGrille() {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                casePanelTable[i][j].clearCase();
                validate();
            }
            
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
