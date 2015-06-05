package implementations;

import iRobotSMA.Ihm;
import interfaces.IControl;
import interfaces.IInfos;

public class IhmImpl extends Ihm{

	@Override
	protected IInfos make_infosFromIhm() {
		// TODO Auto-generated method stub
		return new IInfos() {
			
			@Override
			public void sendData() {
				// TODO Auto-generated method stub
				System.out.println("IHM : Envoie des données");
			}
		};
	}

	@Override
	protected IControl make_controlToEnv() {
		// TODO Auto-generated method stub
		return new IControl() {
			
			@Override
			public void sauvegarder() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reprendre() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void lancerSystem() {
				// TODO Auto-generated method stub
				System.out.println("On lance le cycle de chaque robot");
				requires().creationEcosystemFromEcoProxyAndRobot().listerEspece();
			}
			
			@Override
			public void init() {
				// TODO Auto-generated method stub
				requires().creationEcosystemFromEcoProxyAndRobot().createEspece(0, 0, "RED");
			}
			
			@Override
			public void chargerEtat() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
