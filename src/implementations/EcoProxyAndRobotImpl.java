package implementations;

import iRobotSMA.EcoProxy;
import iRobotSMA.EcoProxyAndRobot;
import iRobotSMA.EcoRobot;
import interfaces.ICreationEcosystem;

import java.util.HashMap;
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
			public boolean createEspece(Integer posX, Integer posY, String couleur) {
				// TODO Auto-generated method stub
				boolean b = false;
				Integer idToCreate = getNextId();
				if (!map.containsKey(idToCreate)){
					ProxyAndRobot.Component par = newProxyAndRobot(idToCreate, posX, posY, couleur) ;
					map.put(idToCreate, par);
					b = true;
				}
				System.out.println(map.toString());
				//parts().proxys().
				
				return b;
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
			public void listerEspece() {
				// TODO Auto-generated method stub
				System.out.println(map.toString());
				//ajout requires
			}
		};
	}

}
