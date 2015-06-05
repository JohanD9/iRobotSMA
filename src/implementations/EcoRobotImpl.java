package implementations;

import iRobotSMA.EcoRobot;
import ihm.Couleur;
import ihm.Type;

import java.util.ArrayList;
import java.util.List;

public class EcoRobotImpl extends EcoRobot{
	
	public RobotImpl robot;
	public List<RobotImpl> l = new ArrayList<RobotImpl>();

	@Override
	protected Robot make_Robot(Integer id, Integer posX, Integer posY, Type type, Couleur color) {
		// TODO Auto-generated method stub
		RobotImpl r = new RobotImpl(id, posX, posY, type, color);
		this.robot = r;
		l.add(robot);
		System.out.println("CREATION ROBOT");
		return r;
	}
	
	public List<RobotImpl> getL() {
		return this.l;
	}
}
