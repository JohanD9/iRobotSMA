package implementations;

import iRobotSMA.EcoProxy;

import java.util.HashMap;
import java.util.Map;

public class EcoProxyImpl extends EcoProxy{
	
	public Map<Integer, ProxyImpl> proxysMap = new HashMap<Integer, ProxyImpl>();

	@Override
	protected Proxy make_Proxy(Integer id) {
		// TODO Auto-generated method stub
		ProxyImpl proxy = new ProxyImpl(id);
		proxysMap.put(id, proxy);
		System.out.println("CREATION PROXY");
		System.out.println(proxysMap.toString());
		return proxy;
	}

}
