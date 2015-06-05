package implementations;

import iRobotSMA.Perception;
import interfaces.IPerceptionRobot;

public class PerceptionImpl extends Perception{
	
	public PerceptionImpl(){
	}

	@Override
	protected IPerceptionRobot make_perceptionRobotToDecision() {
		// TODO Auto-generated method stub
		return new IPerceptionRobot() {
			
			@Override
			public Boolean percevoir() {
				// TODO Auto-generated method stub
				requires().infosFromRobot().sendData();
				System.out.println("PERCEPTION : TRUE");
				return true;
			}
		};
	}

}
