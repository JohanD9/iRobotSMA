package implementations;

import iRobotSMA.Decision;
import ihm.ActionType;
import ihm.Case;
import ihm.Choix;
import ihm.Position;
import interfaces.IDecisionRobot;

import java.util.ArrayList;
import java.util.Random;

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
				if (listCase.size() > 0) {
					Random rand = new Random();
					int tmp = rand.nextInt(listCase.size());
					Case toGo = listCase.get(tmp);
					Choix c = new Choix(ActionType.SE_DEPLACER, new Position(toGo.abscisseCase, toGo.ordonneeCase));
					
					return c;
				}
				return null;
			}
		};
	}

}
