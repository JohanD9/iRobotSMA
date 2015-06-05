package implementations;

import iRobotSMA.EcoRobot;
import ihm.Composant;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.IRobot;

import java.util.ArrayList;
import java.util.List;

public class EcoRobotImpl extends EcoRobot{
	
	public RobotImpl robot;
	public List<RobotImpl> l = new ArrayList<RobotImpl>();

	@Override
	protected Robot make_Robot(Integer id, Position pos, Type type, Couleur color) {
		// TODO Auto-generated method stub
		RobotImpl r = new RobotImpl(id, pos, type, color);
		this.robot = r;
		l.add(robot);
		System.out.println("CREATION ROBOT");
		return r;
	}
	
	public List<RobotImpl> getL() {
		return this.l;
	}

	@Override
	protected IRobot make_robotToEcoProxyAndRobot() {
		// TODO Auto-generated method stub
		return new IRobot() {
			
			@Override
			public List<Composant> getRobots() {
				// TODO Auto-generated method stub
				
				List<Composant> liste = new ArrayList<Composant>();
				Composant c;
				
				for (RobotImpl robot : l){
					c = new Composant(robot.id, robot.type, robot.couleur, robot.position);
					liste.add(c);
				}
				
				return liste;
			}
		};
	}

	
}
/*

*/