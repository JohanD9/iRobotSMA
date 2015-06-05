package implementations;

import iRobotSMA.Action;
import ihm.Choix;
import ihm.Position;
import interfaces.IActionRobot;

public class ActionImpl extends Action{
	
	public ActionImpl() {
		
	}

	@Override
	protected IActionRobot make_actionRobotToRobot() {
		// TODO Auto-generated method stub
		return new IActionRobot() {
			
			@Override
			public void agir(Position pos) {
				Choix c = requires().decisionRobotFromDecision().decider(pos);
			}
		};
	}

}
