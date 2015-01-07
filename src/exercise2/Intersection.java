package exercise2;

import greenfoot.Actor;

public class Intersection {
	
	private int x;
	private int y;
	
	private NSRoad nsRoad;
	private EWRoad ewRoad;
	
	private TrafficLight northLight;
	private TrafficLight southLight;
	private TrafficLight eastLight;
	private TrafficLight westLight;
	
	public Intersection(TrafficWorld world, NSRoad nsRoad, EWRoad ewRoad, Direction initialRed, int redCount, int yellowCount) {
		
		this.nsRoad = nsRoad;
		this.ewRoad = ewRoad;
		
		this.x = nsRoad.getX() + NSRoad.ROAD_WIDTH/2;
		this.y = ewRoad.getY() + EWRoad.ROAD_WIDTH/2;
		
		northLight = new TrafficLight(world, Direction.NORTH, x, y, redCount, yellowCount);
		southLight = new TrafficLight(world, Direction.SOUTH, x, y, redCount, yellowCount);
		eastLight = new TrafficLight(world, Direction.EAST, x, y, redCount, yellowCount);
		westLight = new TrafficLight(world, Direction.WEST, x, y, redCount, yellowCount);
		
		if (initialRed == Direction.NORTH || initialRed == Direction.SOUTH) {
			northLight.setRed();
			southLight.setRed();
			eastLight.setGreen();
			westLight.setGreen();
		} else {
			northLight.setGreen();
			southLight.setGreen();
			eastLight.setRed();
			westLight.setRed();
		}
	}
	
	public void setNSRed() {
		northLight.setRed();
		southLight.setRed();
		eastLight.setGreen();
		westLight.setGreen();
	}
	
	public void setEWRed() {
		northLight.setGreen();
		southLight.setGreen();
		eastLight.setRed();
		westLight.setRed();
	}

	public void setNSYellow() {
		northLight.setYellow();
		southLight.setYellow();
		eastLight.setRed();
		westLight.setRed();
	}
	
	public void setEWYellow() {
		northLight.setRed();
		southLight.setRed();
		eastLight.setYellow();
		westLight.setYellow();
	}
	
	public boolean isYellow(Direction d) {
		boolean result = false;
		switch (d) {
		case NORTH:
		case SOUTH:
			result = northLight.isYellow();
			break;
		case EAST:
		case WEST:
			result = eastLight.isYellow();
			break;
		}
		return result;
	}
	
	public boolean isRed(Direction d) {
		boolean result = false;
		switch (d) {
		case NORTH:
		case SOUTH:
			result = northLight.isRed();
			break;
		case EAST:
		case WEST:
			result = eastLight.isRed();
			break;
		}
		return result;
	}
	
	public boolean isGreen(Direction d) {
		boolean result = false;
		switch (d) {
		case NORTH:
		case SOUTH:
			result = northLight.isGreen();
			break;
		case EAST:
		case WEST:
			result = eastLight.isGreen();
			break;
		}
		return result;
	}
	
	public boolean isIn(Car car) {
		return (nsRoad.isOn(car)) && (ewRoad.isOn(car));
	}
	
	public boolean isApproaching(Car car) {
		return (nsRoad.isOn(car) && ewRoad.isApproaching(car)) || (nsRoad.isApproaching(car) && ewRoad.isOn(car));
	}
	
	public boolean isTooFar(Car car) {
		return ewRoad.isTooFar(car) || nsRoad.isTooFar(car);
	}
}
