package interfaces;

import ihm.Position;
import implementations.RobotImpl;

import java.util.List;

public interface IRobot {
	public List<RobotImpl> getRobots();
	public void updateRobot (RobotImpl robot);
	
}
