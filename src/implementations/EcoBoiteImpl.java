package implementations;

import iRobotSMA.EcoBoite;
import ihm.Composant;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.IBoite;
import interfaces.ICreationEcosystem;

import java.util.ArrayList;
import java.util.List;

public class EcoBoiteImpl extends EcoBoite{
	
	public List<BoiteImpl> boites = new ArrayList<BoiteImpl>();
	public Integer id = 0;

	@Override
	protected ICreationEcosystem make_creationEcoBoite() {
		// TODO Auto-generated method stub
		return new ICreationEcosystem() {
			
			@Override
			public List<Composant> listerEspece() {
				// TODO Auto-generated method stub
				List<Composant> list = new ArrayList<Composant>();
				Composant c;
				
				for (BoiteImpl b : boites){
					c = new Composant(b.id, b.type, b.couleur, b.position);
					list.add(c);
				}
				
				return list;
			}
			
			@Override
			public Integer getNextId() {
				// TODO Auto-generated method stub
				Integer idToReturn = 0;
				synchronized (id) {
					idToReturn = id;
					id ++;
				}
				return idToReturn;
			}
			
			@Override
			public boolean createEspece(Position pos, Type type, Couleur couleur) {
				// TODO Auto-generated method stub		
				Integer idToCreate = getNextId();
				BoiteImpl boite = new BoiteImpl(idToCreate, pos, couleur, type);	

				return boites.add(boite);
			}
		};
	}

	@Override
	protected IBoite make_listBoites() {
		return new IBoite() {
			
			@Override
			public List<BoiteImpl> getBoites() {
				return boites;
			}
		};
	}

}
