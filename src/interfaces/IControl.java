package interfaces;

import java.util.ArrayList;

import ihm.Position;

public interface IControl {
	public void init();
	public int getNbRobot();
	public int getNbBoite();
	public ArrayList<Position> getPosNid();
	public void lancerSystem();
	public void pause();
	public void reprendre();
	public void sauvegarder();
	public void chargerEtat();
	public Position randomPosition();
}
