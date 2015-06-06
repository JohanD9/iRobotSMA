package implementations;

import iRobotSMA.Action;
import iRobotSMA.Decision;
import iRobotSMA.EcoRobot.Robot;
import iRobotSMA.Perception;
import ihm.Couleur;
import ihm.Position;
import ihm.Type;
import interfaces.IActionRobot;

public class RobotImpl extends Robot{
	public Integer id;
	public Position position;
	public Type type;
	public Couleur couleur;
	public Thread t;
	public boolean isRunning = false;
	
	public RobotImpl (Integer id, Position pos, Type type, Couleur color){
		this.id = id;
		this.position = pos;
		this.couleur = color;
		this.type = type;
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
	
	public void lancer() {
		if (!isRunning){
			t = new Thread() {
				public void run() {
					while (true) {
						Position p = make_actionRobotToEcoRobot().agir(position);
						position = p;
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
			isRunning = true;
		} else {
			//reveiller le thread
			t.resume();
		}
	}
	
	public void pause (){
		t.suspend();
	}
	
	public void avancer(){
		Position p = make_actionRobotToEcoRobot().agir(position);
		position = p;
	}
}
