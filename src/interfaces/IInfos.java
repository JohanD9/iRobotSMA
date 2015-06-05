package interfaces;

import ihm.Case;
import ihm.Grille;
import ihm.Position;

import java.util.ArrayList;

public interface IInfos {
	public ArrayList<Case> sendData(Position pos);
	public Grille getGrille();
}
