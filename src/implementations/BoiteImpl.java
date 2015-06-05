package implementations;

import iRobotSMA.EcoBoite.Boite;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;

public class BoiteImpl extends Boite{
	public Integer id;
	public Position position;
	public Couleur couleur;
	public Type type;
	
	public BoiteImpl(Integer id, Position pos, Couleur color, Type type) {
		this.id = id;
		this.position = pos;
		this.couleur = color;
		this.type = type;
	}
}
