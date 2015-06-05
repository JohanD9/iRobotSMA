package interfaces;

import ihm.Couleur;
import ihm.Type;

public interface ICreationEcosystem {
	public boolean createEspece(Integer posX, Integer posY, Type type, Couleur couleur);
	public Integer getNextId();
	public void listerEspece();
}
