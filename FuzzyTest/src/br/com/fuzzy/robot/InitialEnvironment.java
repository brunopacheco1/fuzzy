package br.com.fuzzy.robot;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class InitialEnvironment extends EnvironmentDescription {

	public InitialEnvironment() {
		super();

		light1IsOn = true;
		light2IsOn = false;

		Wall wall1 = new Wall(new Vector3d(25, 0, 0), 10 /*length*/, 2/*height*/, this);
		wall1.rotate90(1);
		wall1.setColor(new Color3f(Color.ORANGE));
		add(wall1);

		Wall wall2 = new Wall(new Vector3d(-25, 0, 0), 10, 2, this);
		wall2.rotate90(1);
		wall2.setColor(new Color3f(Color.MAGENTA));
		add(wall2);

		Wall wall3 = new Wall(new Vector3d(0, 0, 5), 50, 2, this);
		wall3.setColor(new Color3f(Color.DARK_GRAY));
		add(wall3);

		Wall wall4 = new Wall(new Vector3d(0, 0, -5), 50, 2, this);
		wall4.setColor(new Color3f(Color.GREEN));
		add(wall4);

		add(new Robot(new Vector3d(-20, 0, 0), "robot 1"));
		add(new Robot(new Vector3d(-20, 0, -2), "robot 2"));
		add(new Robot(new Vector3d(-20, 0, 2), "robot 3"));
	}
}