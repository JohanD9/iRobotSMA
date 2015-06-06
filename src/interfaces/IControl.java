package interfaces;

import ihm.Couleur;
import ihm.Position;
import ihm.Type;

import java.util.ArrayList;

public interface IControl {
	public void init();
	public int getNbRobot();
	public int getNbBoite();
	public ArrayList<Position> getPosNid();
	public void lancerSystem();
	public void pause();
	public void reprendre();
	public void sauvegarder();
	public int charger(Position pos, Type type, Couleur couleur);
	public void chargerEtat();
	public Position randomPosition();
	public void viderSystem();
}
