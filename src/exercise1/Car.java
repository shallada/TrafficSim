package exercise1;
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
	 
	 public Car() {
		 int carNo = Greenfoot.getRandomNumber(carImages.length);
		 setImage(carImages[carNo]);
	 }
	 
	 public void act() {
		 move(1);
		 switch (this.getRotation()) {
		 case 0:
			 if ((getX() + getImage().getWidth()/2) >= TrafficWorld.WORLD_WIDTH) this.setLocation(0, getY());
			 break;
		 case 90:
			 if ((getY() + getImage().getWidth()/2) >= TrafficWorld.WORLD_HEIGHT) this.setLocation(getX(), 0);
			 break;
		 case 180:
			 if ((getX()) <= 0) this.setLocation(TrafficWorld.WORLD_WIDTH-1, getY());
			 break;
		 case 270:
			 if ((getY()) <= 0) this.setLocation(getX(), TrafficWorld.WORLD_HEIGHT-1);
			 break;
		 }
	 }
}
