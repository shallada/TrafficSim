package exercise2;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor {
	private final static GreenfootImage redLightImage = new GreenfootImage("images/trafficLightRed.png");
	private final static GreenfootImage yellowLightImage = new GreenfootImage("images/trafficLightYellow.png");
	private final static GreenfootImage greenLightImage = new GreenfootImage("images/trafficLightGreen.png");
	
	private Direction direction;
	private int x;
	private int y;
	
	private int redCount;
	private int yellowCount;
	private int greenCount;
	
	private int currentRed = 0;
	private int currentYellow = 0;
	private int currentGreen = 0;

	
	public TrafficLight(TrafficWorld world, Direction direction, int centerX, int centerY, int redCount, int yellowCount) {
		this.direction = direction;
		
		this.redCount = redCount;
		this.yellowCount = yellowCount;
		this.greenCount = redCount - yellowCount;
		
		currentRed = redCount;
		
		switch (direction) {
		case NORTH:
			x = centerX;
			y = centerY - redLightImage.getHeight()/2 - redLightImage.getWidth();
			break;
		case SOUTH:
			x = centerX;
			y = centerY + redLightImage.getHeight()/2 + redLightImage.getWidth();
			break;
		case EAST:
			x = centerX + redLightImage.getHeight()/2 + redLightImage.getWidth();
			y = centerY;
			break;
		case WEST:
			x = centerX - redLightImage.getHeight()/2 - redLightImage.getWidth();
			y = centerY;
			break;
		}
		setRotation(direction.getRotation() + 270);
		setRed();
		world.addObject(this, x, y);
	}
	
	public void setRed() {
		setImage(redLightImage);
		currentRed = redCount;
		currentYellow = 0;
		currentGreen = 0;
	}
	
	public boolean isRed() {
		return getImage() == redLightImage;
	}
	
	public void setYellow() {
		setImage(yellowLightImage);
		currentRed = 0;
		currentYellow = yellowCount;
		currentGreen = 0;
	}
	
	public boolean isYellow() {
		return getImage() == yellowLightImage;
	}
	
	public void setGreen() {
		setImage(greenLightImage);
		currentRed = 0;
		currentYellow = 0;
		currentGreen = greenCount;
	}
	
	public boolean isGreen() {
		return getImage() == greenLightImage;
	}
	
	public void act() {
		if (isRed()) {
			currentRed--;
			if (currentRed == 0) {
				setGreen();
			}
		} else if (isYellow()) {
			currentYellow--;
			if (currentYellow == 0) {
				setRed();
			}
		} else {
			currentGreen--;
			if (currentGreen == 0) {
				setYellow();
			}
		}
	}
}
