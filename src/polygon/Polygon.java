package polygon;

public class Polygon {

	private int[] x = new int[0];
	private int[] y = new int[0];
	
	public Polygon(){}
	
	public Polygon(int[] x, int[] y) {
		this.x = x;
		this.y = y;
	}
	
	public void addPoint(int xPoint, int yPoint) {
		x = extendArray(x);
		x[x.length-1] = xPoint;
		y = extendArray(y);
		y[y.length-1] = yPoint;
	}
	
	public int[] getXPoints() {
		return x;
	}
	
	public int[] getYPoints() {
		return y;
	}
	
	private int[] extendArray(int[] array) {
		int[] newArray = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}
}
