package main;

import iRobotSMA.Env;
import implementations.EnvImpl;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Env.Component e = (new EnvImpl()).newComponent();
	}
}
