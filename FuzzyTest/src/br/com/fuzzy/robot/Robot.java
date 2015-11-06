package br.com.fuzzy.robot;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.vecmath.Vector3d;

import net.sourceforge.jFuzzyLogic.FIS;
import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.LampActuator;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Robot extends Agent {

	private RangeSensorBelt sonars;
	private CameraSensor camera;
	private LampActuator lamp;
	
	private FIS fis;

	public Robot(Vector3d position, String name) {
		super(position, name);
		
		camera = RobotFactory.addCameraSensor(this);
 		
		sonars = RobotFactory.addSonarBeltSensor(this, 5);
 		
 		lamp = RobotFactory.addLamp(this);
 		lamp.setBlink(false);
 	    lamp.setOn(false);
 	      
		lerFuzzyScript();
	}

	private void lerFuzzyScript() {
		String fileName = "fcl/robo_fuzzy.fcl";
		fis = FIS.load(fileName, false);
	}

	public void initBehavior() {
		lerFuzzyScript();
	}

	public void performBehavior() {
		Double distanciaSensorFrente = sonars.getFrontQuadrantMeasurement();
		Double distanciaSensorFrenteDireito = sonars.getFrontRightQuadrantMeasurement();
		Double distanciaSensorLateralDireito = sonars.getRightQuadrantMeasurement();
		Double distanciaSensorLateralEsquerdo = sonars.getLeftQuadrantMeasurement();
		Double distanciaSensorFrenteEsquerdo = sonars.getFrontLeftQuadrantMeasurement();
		
		fis.setVariable("sensor_lateral_esquerdo", distanciaSensorLateralEsquerdo.doubleValue());
		fis.setVariable("sensor_frente_esquerdo", distanciaSensorFrenteEsquerdo.doubleValue());
		fis.setVariable("sensor_frente", distanciaSensorFrente.doubleValue());
		fis.setVariable("sensor_frente_direito", distanciaSensorFrenteDireito.doubleValue());
		fis.setVariable("sensor_lateral_direito", distanciaSensorLateralDireito.doubleValue());
		
		fis.evaluate();
		
		BigDecimal motor = new BigDecimal(fis.getVariable("motor").getValue()).setScale(2, RoundingMode.DOWN);
		setTranslationalVelocity(motor.doubleValue() * 2);
		
		BigDecimal lampada = new BigDecimal(fis.getVariable("lampada").getValue()).setScale(1, RoundingMode.HALF_EVEN).setScale(0, RoundingMode.HALF_DOWN);
		lamp.setBlink(lampada.intValue() == 0);
		
		if(getName().equals("robot 2") && getCounter() % 20 == 0 && !this.collisionDetected()) {
//			// print each sonars measurement
//			System.out.println("-----------------SONARS-----------------");
//			for (int i = 0; i < sonars.getNumSensors(); i++) {
//				double range = sonars.getMeasurement(i);
//				double angle = sonars.getSensorAngle(i);
//				boolean hit = sonars.hasHit(i);
//				
//				System.out.println("SONAR[" + i + "]: at angle = " + angle + "; measured range = " + range + "; has hit something: " + hit);
//			}
//			System.out.println("-----------------FIM-----------------");
		}
	}
}