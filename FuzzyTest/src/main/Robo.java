package main;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class Robo {

	public static void main(String[] args) throws Exception {
		// Load from 'FCL' file
		String fileName = "fcl/robo_fuzzy.fcl";
		FIS fis = FIS.load(fileName, false);

		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		
		JDialogFis jdf = new JDialogFis(fis);
		
		BigDecimal distancia = BigDecimal.ONE;
		while(true) {
			fis.setVariable("distancia", distancia.doubleValue());
			fis.evaluate();
			
			BigDecimal motor_esquerdo = new BigDecimal(fis.getVariable("motor_esquerdo").getValue()).setScale(2, RoundingMode.DOWN);
			BigDecimal motor_direito = new BigDecimal(fis.getVariable("motor_direito").getValue()).setScale(2, RoundingMode.DOWN);
			
			jdf.repaint();
			
			Thread.sleep(1000);
			 
			if(motor_esquerdo.doubleValue() == 0d && motor_direito.doubleValue() == 0d) {
				break;
			}
			
			distancia = distancia.subtract(new BigDecimal("0.1"));
		}
	}
}