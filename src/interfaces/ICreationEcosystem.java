package interfaces;

import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import implementations.RobotImpl;

import java.util.List;

public interface ICreationEcosystem {
	public boolean createEspece(Position pos, Type type, Couleur couleur);
	public Integer getNextId();
	public List<RobotImpl> listerEspece();
}
