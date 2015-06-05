/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

/**
 *
 * @author Johan
 */
public class Composant {
    public Type type;
    public Couleur couleur;
    public int id;

    public Composant(int id, Type type, Couleur couleur) {
        this.type = type;
        this.couleur = couleur;
        this.id = id;
    }
    
    public String getCouleur() {
        if (couleur.equals(Couleur.BLUE)) {
            return "blue";
        } else if (couleur.equals(Couleur.RED)) {
            return "red";
        } else if (couleur.equals(Couleur.GREEN)) {
            return "green";
        } else {
            return "white";
        }
    }

    @Override
    public String toString() {
        return "[" + type + " n° " + id + " : " + couleur + "]<br/>";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Composant other = (Composant) obj;
        if (this.type != other.type) {
            return false;
        }
        if (this.couleur != other.couleur) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
