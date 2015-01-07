package exercise1;
import java.awt.Color;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;


public class EWRoad {
	public final static int ROAD_WIDTH = 50;
	
	
	GreenfootImage backgroound;
	int x;
	int y;
	
	
	public EWRoad(GreenfootImage backgorund, int x, int y) {
		this.backgroound = backgorund;
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		backgroound.setColor(Color.GRAY);
		backgroound.fillRect(x, y, TrafficWorld.WORLD_WIDTH, ROAD_WIDTH);
	}
	
	public void addCar(TrafficWorld world, Direction direction) {
		Car car = new Car();
		switch (direction) {
		case EAST:
			car.setRotation(Direction.EAST.getRotation());
			world.addObject(car, Greenfoot.getRandomNumber(TrafficWorld.WORLD_WIDTH), y+(ROAD_WIDTH*3/4));
			break;
		case WEST:
			car.setRotation(Direction.WEST.getRotation());
			world.addObject(car, Greenfoot.getRandomNumber(TrafficWorld.WORLD_WIDTH), y+(ROAD_WIDTH*1/4));
			break;
		}

	}
}
