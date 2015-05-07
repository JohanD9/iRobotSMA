package main;

import implementations.TestComponentImpl;
import test.TestComponentType;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestComponentType.Component c = (new TestComponentImpl()).newComponent();
	}

}
