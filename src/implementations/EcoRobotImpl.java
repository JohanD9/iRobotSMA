package implementations;

import iRobotSMA.EcoRobot;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.IRobot;

import java.util.ArrayList;
import java.util.List;

public class EcoRobotImpl extends EcoRobot{
	
	public List<RobotImpl> robotListe = new ArrayList<RobotImpl>();

	@Override
	protected Robot make_Robot(Integer id, Position pos, Type type, Couleur color) {
		// TODO Auto-generated method stub
		RobotImpl r = new RobotImpl(id, pos, type, color);
		robotListe.add(r);
		return r;
	}

	@Override
	protected IRobot make_robotToEcoProxyAndRobot() {
		// TODO Auto-generated method stub
		return new IRobot() {
			
			@Override
			public List<RobotImpl> getRobots() {
				return robotListe;
			}
		};
	}
	
}