package logic;

import java.util.ArrayList;

import game.Node;

//There are some problems in the logic of the raytracer the other stuff should be fine
public class RayTracer {

	public ArrayList<Node> getRayTrace(int x0, int x1, int y0, int y1, Node[][] graph) {
		ArrayList<Node> rayTrace = null;
		if (checkInputs(x0, x1, y0, y1, graph.length, graph[0].length)) {
			if (x0 == x1) rayTrace = doHorizontal(x0, y0, y1, graph);
			else if (y0 == y1) rayTrace = doVertical(y0, x0, x1, graph);
			else if(Math.abs(x1 - x0) > Math.abs(y1 - y0)) rayTrace = doShallow(x0, x1, y0, y1, graph);
			else rayTrace = doSteep(x0, x1, y0, y1, graph);	
		} else {
			System.out.println("Invalid inputs");
		}
		return rayTrace;
	}
	
	public ArrayList<Node> doSteep(int x0, int x1, int y0, int y1, Node[][] graph) {
		ArrayList<Node> rayTrace = new ArrayList<>();
		double deltax = x1 - x0;
		double deltay = y1 - y0;
		double deltaerr = Math.abs(deltax/deltay);
		double error = deltaerr - 0.5;
		int x = x0;
		if (y0 > y1) {
			int tmp = y0;
			y0 = y1;
			y1 = tmp;
			tmp = x0;
			x0 = x1;
			x1 = tmp;
			x = x0;
		}
		for (int y = y0; y <= y1; y++) {
			rayTrace.add(graph[y][x]);
			error += deltaerr;
			if (error >= 0.5 && x1 < x0) {
				x -= 1;
				rayTrace.add(graph[y][x]);
				error -= 1;
			} else if (error >= 0.5 && x1 > x0) {
				x += 1;
				rayTrace.add(graph[y][x]);
				error -= 1;
			}
		}
		return rayTrace;
	}
	
	public ArrayList<Node> doShallow(int x0, int x1, int y0, int y1, Node[][] graph) {
		ArrayList<Node> rayTrace = new ArrayList<>();
		double deltax = x1 - x0;
		double deltay = y1 - y0;
		double deltaerr = Math.abs(deltay/deltax);
		double error = deltaerr - 0.5;
		int y = y0;
		if (x0 > x1) {
			int tmp = y0;
			y0 = y1;
			y1 = tmp;
			tmp = x0;
			x0 = x1;
			x1 = tmp;
			y = y0;
		}
		for (int x = x0; x <= x1; x++) {
			rayTrace.add(graph[y][x]);
			error += deltaerr;
			if (error >= 0.5 && y1 < y0) {
				y -= 1;
				rayTrace.add(graph[y][x]);
				error -= 1;
			} else if (error >= 0.5 && y1 > y0) {
				y += 1;
				rayTrace.add(graph[y][x]);
				error -= 1;
			}
		}
		return rayTrace;
	}
	
	public ArrayList<Node> doHorizontal(int x, int y0, int y1, Node[][] graph) {
		ArrayList<Node> rayTrace = new ArrayList<>();
		if (y0 > y1) {
			int tmp = y0;
			y0 = y1;
			y1 = tmp;
		}
		for (int y = y0; y <= y1; y++) {
			rayTrace.add(graph[y][x]);
		}
		return rayTrace;
	}
	
	public ArrayList<Node> doVertical(int y, int x0, int x1, Node[][] graph) {
		ArrayList<Node> rayTrace = new ArrayList<>();
		if (x0 > x1) {
			int tmp = x0;
			x0 = x1;
			x1 = tmp;
		}
		for (int x = x0; x <= x1; x++) {
			rayTrace.add(graph[y][x]);
		}
		return rayTrace;
	}
	
	public boolean checkInputs(int x0, int x1, int y0, int y1, int maxX, int maxY) {
		if (x0 < 0) return false;
		else if (x0 >= maxX) return false;
		else if (x1 < 0) return false;
		else if (x1 >= maxX) return false;
		else if (y0 < 0) return false;
		else if (y0 >= maxY) return false;
		else if (y1 < 0) return false;
		else if (y1 >= maxY) return false;
		return true;
	}
}
