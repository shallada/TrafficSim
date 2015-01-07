package exercise2;
import java.awt.Color;
import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;


public class EWRoad {
	public final static int ROAD_WIDTH = 50;
	
	
	private GreenfootImage backgroound;
	private int x;
	private int y;	
	
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
		Car car = new Car(world);
		switch (direction) {
		case EAST:
			car.setDirection(Direction.EAST);
			world.addObject(car, Greenfoot.getRandomNumber(TrafficWorld.WORLD_WIDTH), y+(ROAD_WIDTH*3/4));
			break;
		case WEST:
			car.setDirection(Direction.WEST);
			world.addObject(car, Greenfoot.getRandomNumber(TrafficWorld.WORLD_WIDTH), y+(ROAD_WIDTH*1/4));
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
		return (car.getFrontY() >= y) && (car.getFrontY() < (y + ROAD_WIDTH));
	}
	
	public boolean isApproaching(Car car) {
		boolean result = false;
		switch (car.getDirection()) {
		case EAST:
		case WEST:
			result = false;
			break;
		case NORTH:
			result = (car.getFrontY() >= (y+ROAD_WIDTH)) && (car.getFrontY() < (y + 3*ROAD_WIDTH/2));
			break;
		case SOUTH:
			result = (car.getFrontY() >= (y - ROAD_WIDTH/2)) && (car.getFrontY() < y);
			break;
		}
		return result;
	}

	public boolean isTooFar(Car car) {
		boolean result = false;
		if (car.getDirection().isVertical()) {
			if (car.getDirection() == Direction.SOUTH) {
				result = (car.getFrontY() > (y + ROAD_WIDTH/4));
			} else {
				result = (car.getFrontY() < (y + 3*ROAD_WIDTH/4));
			}
		}
		return result;
	}
}
