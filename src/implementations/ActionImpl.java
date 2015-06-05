package implementations;

import iRobotSMA.Action;
import interfaces.IActionRobot;

public class ActionImpl extends Action{
	
	public ActionImpl() {
		
	}

	@Override
	protected IActionRobot make_actionRobotToRobot() {
		// TODO Auto-generated method stub
		return new IActionRobot() {
			
			@Override
			public Boolean agir() {
				// TODO Auto-generated method stub
				if (requires().decisionRobotFromDecision().decider()){
					System.out.println("ACTION : TRUE");
					return true;
				} else {
					System.out.println("ACTION : FALSE");
					return false;
				}
			}
		};
	}

}
