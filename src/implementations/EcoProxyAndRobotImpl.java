package implementations;

import iRobotSMA.EcoProxy;
import iRobotSMA.EcoProxyAndRobot;
import iRobotSMA.EcoRobot;
import ihm.Composant;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.ICreationEcosystem;
import interfaces.IRobot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoProxyAndRobotImpl extends EcoProxyAndRobot{
	
	public Map<Integer, ProxyAndRobot.Component> map = new HashMap<Integer, ProxyAndRobot.Component>();
	public Integer id = 0;

	@Override
	protected EcoProxy make_proxys() {
		// TODO Auto-generated method stub
		return new EcoProxyImpl();
	}

	@Override
	protected EcoRobot make_robots() {
		// TODO Auto-generated method stub
		return new EcoRobotImpl();
	}

	@Override
	protected ICreationEcosystem make_creationEcosystemToIhm() {
		// TODO Auto-generated method stub
		return new ICreationEcosystem() {

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
			public List<Composant> listerEspece() {
				// TODO Auto-generated method stub
				List<RobotImpl> l = parts().robots().robotToEcoProxyAndRobot().getRobots();
				List<Composant> lc = new ArrayList<Composant>();
				Composant c;
				
				for (RobotImpl r : l){
					c = new Composant(r.id, r.type, r.couleur, r.position);
					lc.add(c);
				}
				return lc;
			}

			@Override
			public boolean createEspece(Position pos, Type type,
					Couleur couleur) {
				// TODO Auto-generated method stub
				boolean b = false;
				Integer idToCreate = getNextId();
				if (!map.containsKey(idToCreate)){
					ProxyAndRobot.Component par = newProxyAndRobot(idToCreate, pos, type, couleur) ;
					map.put(idToCreate, par);
					b = true;
				}
				
				return b;
			}
		};
	}

	@Override
	protected IRobot make_listeRobots() {
		// TODO Auto-generated method stub
		return new IRobot() {
			
			@Override
			public List<RobotImpl> getRobots() {
				// TODO Auto-generated method stub
				return parts().robots().robotToEcoProxyAndRobot().getRobots();
			}
		};
	}

}
