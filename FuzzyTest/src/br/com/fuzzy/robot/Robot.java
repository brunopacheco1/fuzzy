package br.com.fuzzy.robot;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.vecmath.Vector3d;

import net.sourceforge.jFuzzyLogic.FIS;
import simbad.sim.Agent;
import simbad.sim.LampActuator;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Robot extends Agent {

	private RangeSensorBelt sonars;
	private LampActuator lamp;
	
	private FIS fis;

	public Robot(Vector3d position, String name) {
		super(position, name);
		
		sonars = RobotFactory.addSonarBeltSensor(this, 3);
 		
 		lamp = RobotFactory.addLamp(this);
 		lamp.setBlink(false);
 	    lamp.setOn(false);
 	      
		lerFuzzyScript();
	}

	private void lerFuzzyScript() {
		String fileName = "fcl/robo.fcl";
		fis = FIS.load(fileName, false);
	}

	public void initBehavior() {
		lerFuzzyScript();
	}

	public void performBehavior() {
		Double distanciaSensorFrente = sonars.getMeasurement(0);
		Double distanciaSensorLateralDireito = sonars.getMeasurement(1);
		Double distanciaSensorLateralEsquerdo = sonars.getMeasurement(2);
		
		fis.setVariable("sensor_esquerdo", distanciaSensorLateralEsquerdo.doubleValue());
		fis.setVariable("sensor_frente", distanciaSensorFrente.doubleValue());
		fis.setVariable("sensor_direito", distanciaSensorLateralDireito.doubleValue());
		
		fis.evaluate();
		
		BigDecimal motor = new BigDecimal(fis.getVariable("motor").getValue()).setScale(2, RoundingMode.DOWN);
		setTranslationalVelocity(motor.doubleValue());
		
		BigDecimal lampada = new BigDecimal(fis.getVariable("lampada").getValue()).setScale(0, RoundingMode.UP);
		lamp.setBlink(lampada.intValue() > 2 && lampada.intValue() < 4);
		lamp.setOn(lampada.intValue() > 4);
		
		BigDecimal giro = new BigDecimal(fis.getVariable("giro").getValue()).setScale(0, RoundingMode.UP);
		setRotationalVelocity(giro.doubleValue() * 0.0174533);
	}
}