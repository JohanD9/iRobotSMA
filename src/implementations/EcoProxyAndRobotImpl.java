package implementations;

import iRobotSMA.EcoProxy;
import iRobotSMA.EcoProxyAndRobot;
import iRobotSMA.EcoRobot;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.ICreation;
import interfaces.IRobot;

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
	protected IRobot make_listeRobots() {
		// TODO Auto-generated method stub
		return new IRobot() {
			
			@Override
			public List<RobotImpl> getRobots() {
				// TODO Auto-generated method stub
				return parts().robots().robotToEcoProxyAndRobot().getRobots();
			}
			
			@Override
			public void updateRobot(RobotImpl robot) {
				parts().robots().robotToEcoProxyAndRobot().updateRobot(robot);
			}
		};
	}

	@Override
	protected ICreation make_creerEcoSys() {
		// TODO Auto-generated method stub
		return new ICreation() {
			
			@Override
			public void create(Integer id, Position pos, Couleur couleur, Type type) {
				// TODO Auto-generated method stub
				if (!map.containsKey(id)){
					ProxyAndRobot.Component par = newProxyAndRobot(id, pos, type, couleur) ;
					map.put(id, par);
				} else {
					System.out.println("DEJA DANS LA MAP");
				}
			}
		};
	}
}
