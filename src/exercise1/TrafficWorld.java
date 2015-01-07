package exercise1;
import java.awt.Color;

import greenfoot.World;


public class TrafficWorld extends World {
	
	public final static int WORLD_WIDTH = 1000;
	public final static int WORLD_HEIGHT = 750;
	public final static int WORLD_CELL_SIZE = 1;
	
	private final static int N_EW_ROADS = 5;
	private final static int N_NS_ROADS = 7;
	
	public TrafficWorld()
	{    
		super(WORLD_WIDTH, WORLD_HEIGHT, WORLD_CELL_SIZE); 
		getBackground().setColor(Color.GREEN);
		getBackground().fill();
		createRoads();
		createCars();
	}
	
	private EWRoad[] ewRoads = new EWRoad[N_EW_ROADS];
	private NSRoad[] nsRoads = new NSRoad[N_NS_ROADS];
	
	private void createRoads() {
		for (int i = 0; i < N_EW_ROADS; i++) {
			int x = 0;
			int y = i * (WORLD_HEIGHT-EWRoad.ROAD_WIDTH) / (N_EW_ROADS-1);
			EWRoad road = new EWRoad(getBackground(), x, y);
			road.draw();
			ewRoads[i] = road;
		}
		
		for (int i = 0; i < N_NS_ROADS; i++) {
			int x = i * (WORLD_WIDTH-NSRoad.ROAD_WIDTH) / (N_NS_ROADS-1);
			int y = 0;
			NSRoad road = new NSRoad(getBackground(), x, y);
			road.draw();
			nsRoads[i] = road;
		}
	}
	
	private void createCars() {
		for (int i = 0; i < ewRoads.length; i++) {
			ewRoads[i].addCar(this, Direction.EAST);
			ewRoads[i].addCar(this, Direction.WEST);
		}
		for (int i = 0; i < nsRoads.length; i++) {
			nsRoads[i].addCar(this, Direction.NORTH);
			nsRoads[i].addCar(this, Direction.SOUTH);
		}
	}

}
