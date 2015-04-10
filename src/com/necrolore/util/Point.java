package com.necrolore.util;

public class Point extends Pair<Double, Double>{

	/**
	 * Creates a point at the given x and y coords
	 */
	public Point(double x, double y) {
		super(x, y);
		
	}
	
	/**
	 * Gets the distance from this point to the given point
	 */
	public double distance(Point point){
		
		return Math.sqrt(
				Math.pow(getX()-point.getX(), 2)+
				Math.pow(getY()-point.getY(), 2));
		
	}
	
	
	/**
	 * Gets the distance from this point to (x, y)
	 */
	public double distance(double x, double y){
		
		return Math.sqrt(
				Math.pow(getX()-x, 2)+
				Math.pow(getY()-y, 2));
		
	}
	
	
	/**
	 * Gets the x coord of this point
	 */
	public double getX(){
		return getA();
	}
	
	
	/**
	 * Gets the y coord of this point
	 */
	public double getY(){
		return getB();
	}

	
	/**
	 * Gets the result of subtracting the given point from this point (Using element-wise subtraction)
	 */
	public Point subtract(Point point){
		return new Point(getX()-point.getX(), getY()-point.getY());
	}
	
	
	/**
	 * Gets the result of subtracting the given point (x, y) from this point (Using element-wise subtraction)
	 */
	public Point subtract(double x, double y){
		return new Point(getX()-x, getY()-y);
	}
	
	
	/**
	 * Gets the result of adding the given point from this point (Using element-wise addition)
	 */
	public Point add(Point point){
		return new Point(getX()+point.getX(), getY()+point.getY());
	}
	
	
	/**
	 * Gets the result of adding the given point (x, y) from this point (Using element-wise addition)
	 */
	public Point add(double x, double y){
		return new Point(getX()+x, getY()+y);
	}
	
	
	/**
	 * Gets the result of the cross product of this point and the given point
	 */
	public double crossProduct(Point point){
		return getX()*point.getY()-getY()*point.getX();
	}
	
	
	/**
	 * Gets the result of the cross product of this point and the given point (x, y)
	 */
	public double crossProduct(double x, double y){
		return getX()*y-getY()*x;
	}
	
}
