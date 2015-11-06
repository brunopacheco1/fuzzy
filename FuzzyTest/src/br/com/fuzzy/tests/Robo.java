package br.com.fuzzy.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class Robo {

	public static void main(String[] args) throws Exception {
		// Load from 'FCL' file
		String fileName = "fcl/robo.fcl";
		FIS fis = FIS.load(fileName, false);

		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		
		JDialogFis jdf = new JDialogFis(fis);
		
		BigDecimal distancia = new BigDecimal("2.0");
		while(true) {
			fis.setVariable("sensor_frente", distancia.doubleValue());
			fis.evaluate();
			
			BigDecimal motor = new BigDecimal(fis.getVariable("motor").getValue()).setScale(2, RoundingMode.DOWN);
			
			jdf.repaint();
			
			Thread.sleep(1000);
			 
			if(motor.doubleValue() == 0d) {
				break;
			}
			
			distancia = distancia.subtract(new BigDecimal("0.1"));
		}
	}
}