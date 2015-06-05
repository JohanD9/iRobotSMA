package implementations;

import iRobotSMA.EcoProxy.Proxy;
import interfaces.IInfos;

public class ProxyImpl extends Proxy{

	public final Integer id;
	
	public ProxyImpl(Integer id) {
		this.id = id;
	}

	@Override
	protected IInfos make_infosToRobot() {
		// TODO Auto-generated method stub
		return eco_requires().infosFromEcoProxyAndRobot();
	}
}
