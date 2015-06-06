package ihm;

import implementations.RobotImpl;

public class ThreadRobot  implements Runnable{
	RobotImpl robot;
	
	
	
	public ThreadRobot(RobotImpl robot) {
		super();
		this.robot = robot;
	}



	@Override
	public void run() {
		Position newPosRobot = robot.lancer();
		robot.position = newPosRobot;	
	}



	public RobotImpl getRobot() {
		return robot;
	}



	public void setRobot(RobotImpl robot) {
		this.robot = robot;
	}

	
}
