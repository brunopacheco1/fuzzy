package main;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class Robo {

	public static void main(String[] args) throws Exception {
		// Load from 'FCL' file
		String fileName = "fcl/arcondicionado.fcl";
		FIS fis = FIS.load(fileName, false);

		// Error while loading?
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		
		JDialogFis jdf = new JDialogFis(fis, 800, 600);

		int i = -2;
		boolean reverse = false;
		while(true) {
			// Set inputs and evaluate
			fis.setVariable("temperatura", i);
			fis.evaluate();
			
//			Variable potencia = fis.getVariable("potencia");
//			System.out.println(potencia.getValue());
			
			jdf.repaint();

			Thread.sleep(300);
			
			if(reverse) {
				i--;
			} else {
				i++;
			}
			
			if(i == 39) {
				reverse = true;
			} else if(i == -2) {
				reverse = false;
			}
		}
		
	}
}