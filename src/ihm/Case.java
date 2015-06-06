/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author jdebat
 */
public class Case extends javax.swing.JPanel {
    public Grille grille;
    private Dimension dimensionPanel;
    public final int ordonneeCase;
    public final int abscisseCase;
    
    public ArrayList<Composant> listComposants = new ArrayList<>();
    
    public Color greenColor = new Color(188, 255, 181);
    public Color redColor = new Color(255, 181, 181);
    public Color blueColor = new Color(181, 255, 255);
    
    
    /**
     * Creates new form Case
     */
    public Case(Dimension dimPanel, int abscisse, int ordonnee, Grille pere) {
        initComponents();
        myInitComponents();
        abscisseCase = abscisse;
        ordonneeCase = ordonnee;
        grille = pere;
        dimensionPanel = dimPanel;
        
        Color first = new Color(191, 191, 191);
        setSize(dimensionPanel);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(first));
        setLayout(new BorderLayout());
    }

    private void myInitComponents() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                grille.updateInfos(abscisseCase, ordonneeCase);
            }
        });
        
        
    }
    
    public ArrayList<Composant> addComposant(Composant composantToAdd) {
        if (!listComposants.contains(composantToAdd)) {
            listComposants.add(composantToAdd);
        } else {
        	System.out.println("Composant deja dans la case");
        }
        
        String color;
        for (int i = 0; i < listComposants.size(); i++) {
        	Composant composant = listComposants.get(i);
            color = composant.getCouleur();
            if (composant.type.equals(Type.NID)) {
                ImageIcon nidIcon = new ImageIcon(this.getClass().getResource("/resources/nid_" + color + ".png"));
                JLabel imgContainer = new JLabel();
                imgContainer.setIcon(nidIcon);
                imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(imgContainer, BorderLayout.CENTER);
            } else if (composant.type.equals(Type.ROBOT)) {
                ImageIcon robotIcon = new ImageIcon(this.getClass().getResource("/resources/robot_" + color + ".png"));
                JLabel imgContainer = new JLabel();
                imgContainer.setIcon(robotIcon);
                imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(imgContainer, BorderLayout.CENTER); 
            } else if (composant.type.equals(Type.BOITE)) {
                if (color.equals("red")) {
                    this.setBackground(redColor);
                } else if (color.equals("blue")) {
                    this.setBackground(blueColor);
                } else if (color.equals("green")) {
                    this.setBackground(greenColor);
                } else {
                    this.setBackground(Color.WHITE);
                }
            }
        }
        this.setVisible(false);
        this.setVisible(true);
        return listComposants;
    }
    
    public ArrayList<Composant> removeComposant(Composant composantToAdd) {
        if (listComposants.contains(composantToAdd)) {
            listComposants.remove(composantToAdd);
        }
        
        String color;
        this.removeAll();
        
        this.setBackground(Color.BLACK);
        this.setBackground(Color.WHITE);
        for (Composant composant : listComposants) {
            color = composant.getCouleur();
            if (composant.type.equals(Type.NID)) {
                ImageIcon nidIcon = new ImageIcon(this.getClass().getResource("/resources/nid_" + color + ".png"));
                JLabel imgContainer = new JLabel();
                imgContainer.setIcon(nidIcon);
                imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(imgContainer, BorderLayout.CENTER);
            } else if (composant.type.equals(Type.ROBOT)) {
                ImageIcon robotIcon = new ImageIcon(this.getClass().getResource("/resources/robot_" + color + ".png"));
                JLabel imgContainer = new JLabel();
                imgContainer.setIcon(robotIcon);
                imgContainer.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(imgContainer, BorderLayout.CENTER); 
            } else if (composant.type.equals(Type.BOITE)) {
                if (color.equals("red")) {
                    this.setBackground(redColor);
                } else if (color.equals("blue")) {
                    this.setBackground(blueColor);
                } else if (color.equals("green")) {
                    this.setBackground(greenColor);
                } else {
                    this.setBackground(Color.WHITE);
                }
            }
        }
        this.setVisible(false);
        this.setVisible(true);
        return listComposants;
    }
    
    public void clearCase() {
        listComposants.clear();
        removeAll();
        setBackground(Color.BLACK);
        setBackground(Color.WHITE);
    }
    
    public Couleur getCouleurBoite() {
    	for (Composant c : listComposants) {
    		if (c.type == Type.BOITE) {
    			return c.couleur;
    		}
    	}
    	return null;
    }
    
    public Composant getBoite() {
    	for (Composant c : listComposants) {
    		if (c.type == Type.BOITE) {
    			return c;
    		}
    	}
    	return null;
    }
    
    public Boolean getNid(Couleur couleur) {
    	for (Composant c : listComposants) {
    		if (c.type == Type.NID && c.couleur == couleur) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public Boolean hasNid() {
    	for (Composant c : listComposants) {
    		if (c.type == Type.NID) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public Composant removeBoite() {
    	for (Composant c : listComposants) {
    		if (c.type == Type.BOITE) {
    			removeComposant(c);
    			return c;
    		}
    	}
    	return null;
    }
    
    

    @Override
    public String toString() {
        return "Case{" + "ordonneeCase=" + ordonneeCase + ", abscisseCase=" + abscisseCase + ", listComposants=" + listComposants + '}';
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
