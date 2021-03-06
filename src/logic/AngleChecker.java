package logic;

import java.io.Serializable;

public class AngleChecker {
	
	// Needed vectors for each direction
	//UP: I = 1, J = 0;
	//DOWN: I = -1, J = 0
	//LEFT: I = 0, J = 1
	//RIGHT: I = 0, J = -1

	public boolean checkAngle (int vI, int vJ, int uI, int uJ, double angle) {
		boolean angleChecker = false;
		
		double distV = Math.sqrt((Math.pow(vI, 2)) + Math.pow(vJ, 2));
		double distU = Math.sqrt((Math.pow(uI, 2)) + Math.pow(uJ, 2));
		double dotProduct = (vI * uI) + (vJ * uJ);
		
		double preArccos = dotProduct / (distV * distU);
		
		double vectorAngle = Math.toDegrees(Math.acos(preArccos));
		
		if (vectorAngle <= angle) angleChecker = true;
		
		return angleChecker;
	}
}
