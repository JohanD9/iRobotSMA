package implementations;

import iRobotSMA.Action;
import iRobotSMA.Decision;
import iRobotSMA.EcoRobot.Robot;
import iRobotSMA.Perception;
import interfaces.IActionRobot;

public class RobotImpl extends Robot{
	public Integer id;
	public Integer positionX;
	public Integer positionY;
	public String couleur;
	
	public RobotImpl (Integer id, Integer posX, Integer posY, String color){
		this.id = id;
		this.positionX = posX;
		this.positionY = posY;
		this.couleur = color;
	}

	@Override
	protected Perception make_perception() {
		// TODO Auto-generated method stub
		return new PerceptionImpl();
	}

	@Override
	protected Decision make_decision() {
		// TODO Auto-generated method stub
		return new DecisionImpl();
	}

	@Override
	protected Action make_action() {
		// TODO Auto-generated method stub
		return new ActionImpl();
	}

	@Override
	protected IActionRobot make_actionRobotToEcoRobot() {
		// TODO Auto-generated method stub
		return parts().action().actionRobotToRobot();
	}
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		/*System.out.println("START ROBOT");
		System.out.println(this.couleur);
		for (int i=0; i<2; i++){
			make_actionRobotToEcoRobot().agir();
		}*/
	}
}
