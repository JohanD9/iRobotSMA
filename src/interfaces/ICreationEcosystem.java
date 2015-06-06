package interfaces;

import ihm.Composant;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;

import java.util.List;

public interface ICreationEcosystem {
	public int createEspece(Position pos, Type type, Couleur couleur);
	public Integer getNextId(Type type);
	public List<Composant> listerEspece(List<?> listEspece);
	public void removeEspece();
}
