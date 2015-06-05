package implementations;

import iRobotSMA.Perception;
import ihm.Case;
import ihm.Position;
import interfaces.IPerceptionRobot;

import java.util.ArrayList;

public class PerceptionImpl extends Perception{
	
	public PerceptionImpl(){
	}

	@Override
	protected IPerceptionRobot make_perceptionRobotToDecision() {
		// TODO Auto-generated method stub
		return new IPerceptionRobot() {
			
			@Override
			public ArrayList<Case> percevoir(Position pos) {
				// TODO Auto-generated method stub
				return requires().infosFromRobot().sendData(pos);
			}
		};
	}

}
