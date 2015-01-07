package exercise2;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;


public class Car extends Actor {

	final static GreenfootImage[] carImages =  {
		new GreenfootImage("images/topCarBlue.png"),
		new GreenfootImage("images/topCarRed.png"),
		new GreenfootImage("images/topCarYellow.png"),
		new GreenfootImage("images/topCarPurple.png")
	};

	private static final int CRUISING_SPEED = 3;

	private TrafficWorld world;
	private Direction direction = Direction.NORTH;

	private enum State {
		STOPPED, ACCELERATING, MOVING, SLOWING
	}
	private State state = State.STOPPED;
	private int speed = 0;

	public Car(TrafficWorld world) {
		this.world = world;
		int carNo = Greenfoot.getRandomNumber(carImages.length);
		setImage(carImages[carNo]);
	}

	public void setDirection(Direction d) {
		direction = d;
		this.setRotation(direction.getRotation());
	}

	public Direction getDirection() {
		return direction;

	}

	public int getFrontX() {
		int result = getX();
		switch(direction) {
		case NORTH:
		case SOUTH:
			break;
		case EAST:
			result += getImage().getWidth()/2;
			break;
		case WEST:
			result -= getImage().getWidth()/2;
		}
		return result;
	}

	public int getFrontY() {
		int result = getY();
		switch(direction) {
		case EAST:
		case WEST:
			break;
		case SOUTH:
			result += getImage().getWidth()/2;
			break;
		case NORTH:
			result -= getImage().getWidth()/2;
		}
		return result;
	}

	public void act() {

		Intersection containingIntersection = world.findContainingIntersection(this);
		Intersection approachingIntersection = world.findApproachingIntersection(this);

		switch (state) {
		case STOPPED:
			if ((containingIntersection == null) ||
					containingIntersection.isGreen(direction) ||
					containingIntersection.isTooFar(this)) {
				state = State.ACCELERATING;
			}
			break;
		case ACCELERATING:
			if ((approachingIntersection != null) && approachingIntersection.isYellow(direction)) {
				state = State.SLOWING;
			} else {
				if (speed < CRUISING_SPEED) {
					speed++;
				} else {
					state = State.MOVING;
				}
			}
			move(speed);
			break;
		case MOVING:
			if ((approachingIntersection != null) && !approachingIntersection.isGreen(direction)) {
				state = State.SLOWING;
			}
			move(speed);
			break;
		case SLOWING:
			if (approachingIntersection == null) {
				if (containingIntersection == null) {
					state = State.ACCELERATING;
				} else if (!containingIntersection.isGreen(direction)) {
					if (containingIntersection.isTooFar(this)) {
						state = State.ACCELERATING;
					} else {
						if (speed == 1) {
							state = State.STOPPED;
						} else {
							speed--;
						}
					}
				}
			} else if (approachingIntersection.isGreen(direction)) {
				state = State.ACCELERATING;
			} else {
				if (speed > 1) speed--;
			}
			move(speed);
			break;
		}

		switch (direction) {
		case NORTH:
			if ((getY()) <= 0) this.setLocation(getX(), TrafficWorld.WORLD_HEIGHT-1);
			break;
		case SOUTH:
			if ((getY() + getImage().getWidth()/2) >= TrafficWorld.WORLD_HEIGHT) this.setLocation(getX(), 0);
			break;
		case EAST:
			if ((getX() + getImage().getWidth()/2) >= TrafficWorld.WORLD_WIDTH) this.setLocation(0, getY());
			break;
		case WEST:
			if ((getX()) <= 0) this.setLocation(TrafficWorld.WORLD_WIDTH-1, getY());
			break;
		}

	}
}
