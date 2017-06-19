package logic;

public class RadiusChecker {

	public boolean RadiusCheck (int x0, int x1, int y0, int y1, int rad) {
		boolean radiusCheck = false;
		double deltax = x1 - x0;
		double deltay = y1 - y0;
		double dist = Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2));
		if (dist <= rad) radiusCheck = true;
		return radiusCheck;
	}
}
