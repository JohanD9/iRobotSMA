package implementations;

import iRobotSMA.EcoProxyAndRobot;
import iRobotSMA.Env;
import iRobotSMA.Ihm;
import ihm.IhmSma;

public class EnvImpl extends Env{
	
	public EnvImpl() {
		
	}

	@Override
	protected Ihm make_ihm() {
		// TODO Auto-generated method stub
		return new IhmSma();
	}

	@Override
	protected EcoProxyAndRobot make_ecoProxyAndRobot() {
		// TODO Auto-generated method stub
		return new EcoProxyAndRobotImpl();
	}
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		System.out.println("Start EnvImpl");
		//parts().ihm().controlToEnv().init();
		//parts().ihm().controlToEnv().lancerSystem();
	}
}
