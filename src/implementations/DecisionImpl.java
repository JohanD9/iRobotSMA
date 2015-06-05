package implementations;

import iRobotSMA.Decision;
import ihm.Case;
import ihm.Choix;
import ihm.Position;
import interfaces.IDecisionRobot;

import java.util.ArrayList;

public class DecisionImpl extends Decision{
	
	public DecisionImpl() {
		
	}

	@Override
	protected IDecisionRobot make_decisionRobotToAction() {
		// TODO Auto-generated method stub
		return new IDecisionRobot() {
			
			@Override
			public Choix decider(Position pos) {
				ArrayList<Case> listCase = requires().perceptionRobotFromPerception().percevoir(pos);
				Choix c = null;
				
				return c;
			}
		};
	}

}
