package exercise2;

import greenfoot.Actor;

import java.util.ArrayList;

public class IntersectionSet {

	private ArrayList<Intersection> intersections = new ArrayList<Intersection>();

	public IntersectionSet(TrafficWorld world, EWRoad[] ewRoads, NSRoad[] nsRoads) {
		for (EWRoad ewRoad : ewRoads) {
			for (NSRoad nsRoad : nsRoads) {
				Intersection intersection = new Intersection(world, nsRoad,ewRoad, Direction.EAST, 300, 50);
				intersections.add(intersection);
			}
		}
	}
	
	public Intersection findNearIntersection(Car car) {
		Intersection result = null;
		for (int i = 0; (result == null) && (i < intersections.size()); i++) {
			Intersection current = intersections.get(i);
			if (current.isApproaching(car) || current.isIn(car)) result = current;
		}
		return result;
	}
	
	public Intersection findApproachingIntersection(Car car) {
		Intersection result = null;
		for (int i = 0; (result == null) && (i < intersections.size()); i++) {
			Intersection current = intersections.get(i);
			if (current.isApproaching(car)) result = current;
		}
		return result;
	}
	
	public Intersection findContainingIntersection(Car car) {
		Intersection result = null;
		for (int i = 0; (result == null) && (i < intersections.size()); i++) {
			Intersection current = intersections.get(i);
			if (current.isIn(car)) result = current;
		}
		return result;
	}
	
	
}
