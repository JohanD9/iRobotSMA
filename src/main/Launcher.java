package main;

import test.ComponentServerType;
import implementations.ComponentServerImpl;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComponentServerType.Component c = (new ComponentServerImpl()).newComponent();
		System.out.println(c.nomPortServiceContraire().serviceContraire(false));
	}

}
