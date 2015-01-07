package exercise1;
import java.awt.Color;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;


public class NSRoad {
	public final static int ROAD_WIDTH = 50;
	
	
	GreenfootImage backgroound;
	int x;
	int y;
	
	
	public NSRoad(GreenfootImage backgorund, int x, int y) {
		this.backgroound = backgorund;
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		backgroound.setColor(Color.GRAY);
		backgroound.fillRect(x, y, ROAD_WIDTH, TrafficWorld.WORLD_HEIGHT);
	}

	public void addCar(TrafficWorld world, Direction direction) {
		Car car = new Car();
		switch (direction) {
		case NORTH:
			car.setRotation(Direction.NORTH.getRotation());
			world.addObject(car, x+(ROAD_WIDTH*3/4), Greenfoot.getRandomNumber(TrafficWorld.WORLD_HEIGHT));
			break;
		case SOUTH:
			car.setRotation(Direction.SOUTH.getRotation());
			world.addObject(car, x+(ROAD_WIDTH*1/4), Greenfoot.getRandomNumber(TrafficWorld.WORLD_HEIGHT));
			break;
		}
	}
}
