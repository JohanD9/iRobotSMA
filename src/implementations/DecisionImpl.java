package implementations;

import iRobotSMA.Decision;
import interfaces.IDecisionRobot;

public class DecisionImpl extends Decision{
	
	public DecisionImpl() {
		
	}

	@Override
	protected IDecisionRobot make_decisionRobotToAction() {
		// TODO Auto-generated method stub
		return new IDecisionRobot() {
			
			@Override
			public Boolean decider() {
				// TODO Auto-generated method stub
				if (requires().perceptionRobotFromPerception().percevoir())
				{
					System.out.println("DECISION : TRUE");
					return true;
				} else {
					System.out.println("DECISION : FALSE");
					return false;
				}
			}
		};
	}

}
