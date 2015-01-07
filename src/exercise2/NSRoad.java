package exercise2;
import java.awt.Color;
import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;


public class NSRoad {
	public final static int ROAD_WIDTH = 50;
	
	
	private GreenfootImage backgroound;
	private int x;
	private int y;	
	
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
		Car car = new Car(world);
		switch (direction) {
		case NORTH:
			car.setDirection(Direction.NORTH);
			world.addObject(car, x+(ROAD_WIDTH*3/4), Greenfoot.getRandomNumber(TrafficWorld.WORLD_HEIGHT));
			break;
		case SOUTH:
			car.setDirection(Direction.SOUTH);
			world.addObject(car, x+(ROAD_WIDTH*1/4), Greenfoot.getRandomNumber(TrafficWorld.WORLD_HEIGHT));
			break;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isOn(Car car) {
		return (car.getFrontX() >= x) && (car.getFrontX() < (x + ROAD_WIDTH));
	}
	
	public boolean isApproaching(Car car) {
		boolean result = false;
		switch (car.getDirection()) {
		case NORTH:
		case SOUTH:
			result = false;
			break;
		case EAST:
			result = (car.getFrontX() >= (x - ROAD_WIDTH/2)) && (car.getFrontX() < x);
			break;
		case WEST:
			result = (car.getFrontX() >= (x+ROAD_WIDTH)) && (car.getFrontX() < (x + 3*ROAD_WIDTH/2));
			break;
		}
		return result;
	}
	
	public boolean isTooFar(Car car) {
		boolean result = false;
		if (car.getDirection().isHorizontal()) {
			if (car.getDirection() == Direction.EAST) {
				result = (car.getFrontX() > (x + ROAD_WIDTH/4));
			} else {
				result = (car.getFrontX() < (x + 3*ROAD_WIDTH/4));
			}
		}
		return result;
	}
}
