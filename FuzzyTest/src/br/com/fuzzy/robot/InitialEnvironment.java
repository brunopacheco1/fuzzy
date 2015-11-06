package br.com.fuzzy.robot;

import javax.vecmath.Vector3d;

import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class InitialEnvironment extends EnvironmentDescription {

	public InitialEnvironment() {
		super();

		light1IsOn = true;
		light2IsOn = false;

		Wall localWall1 = new Wall(new Vector3d(9.0D, 0.0D, 0.0D), 19.0F, 1.0F, this);
	    localWall1.rotate90(1);
	    add(localWall1);
	    Wall localWall2 = new Wall(new Vector3d(-9.0D, 0.0D, 0.0D), 19.0F, 2.0F, this);
	    localWall2.rotate90(1);
	    add(localWall2);
	    Wall localWall3 = new Wall(new Vector3d(0.0D, 0.0D, 9.0D), 19.0F, 1.0F, this);
	    add(localWall3);
	    Wall localWall4 = new Wall(new Vector3d(0.0D, 0.0D, -9.0D), 19.0F, 2.0F, this);
	    add(localWall4);

		Robot robot1 = new Robot(new Vector3d(-7, 0, 0), "robot 1");
		Robot robot2 = new Robot(new Vector3d(-7, 0, -2), "robot 2");
		Robot robot3 = new Robot(new Vector3d(-7, 0, 2), "robot 3");
		
		Robot robot4 = new Robot(new Vector3d(7, 0, 0), "robot 4");
		Robot robot5 = new Robot(new Vector3d(7, 0, -2), "robot 5");
		Robot robot6 = new Robot(new Vector3d(7, 0, 2), "robot 6");
		
		Robot robot7 = new Robot(new Vector3d(5, 0, 0), "robot 7");
		Robot robot8 = new Robot(new Vector3d(5, 0, -2), "robot 8");
		Robot robot9 = new Robot(new Vector3d(5, 0, 2), "robot 9");
		
		Robot robot10 = new Robot(new Vector3d(-5, 0, 0), "robot 10");
		Robot robot11 = new Robot(new Vector3d(-5, 0, -2), "robot 11");
		Robot robot12 = new Robot(new Vector3d(-5, 0, 2), "robot 12");
		
		add(robot1);
		add(robot2);
		add(robot3);
		add(robot4);
		add(robot5);
		add(robot6);
		add(robot7);
		add(robot8);
		add(robot9);
		add(robot10);
		add(robot11);
		add(robot12);
	}
}