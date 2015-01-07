package exercise2;

public enum Direction {
	NORTH(270),
	SOUTH(90),
	EAST(0),
	WEST(180);
	
	private int rotation;
	
	Direction(int rotation) {
		this.rotation = rotation;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public boolean isHorizontal() {
		return (this == EAST) || (this == WEST);
	}
	
	public boolean isVertical() {
		return (this == NORTH) || (this == SOUTH);
	}
}
