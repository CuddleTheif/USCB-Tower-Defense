package com.necrolore.util;

public class Line {
	
	private Point start, end;
	
	
	/**
	 * Creates a line from the start point to the end point
	 */
	public Line(Point start, Point end){
		this.start = start;
		this.end = end;
	}
	
	
	/**
	 * Creates a line from (x1, y1) to (x2, y2)
	 */
	public Line(double x1, double y1, double x2, double y2){
		this(new Point(x1, y1), new Point(x2, y2));
	}

	
	/**
	 * Creates a copy of the given line
	 */
	public Line(Line line){
		this(line.start, line.end);
	}
	
	
	/**
	 * Gets the start point of this line
	 */
	public Point getStart(){
		return start;
	}
	
	
	/**
	 * Gets the end point of this line
	 */
	public Point getEnd(){
		return end;
	}
	
	
	/**
	 * Checks if this line intersects the given line
	 */
	public boolean intersects(Line line){
		return boundingBoxIntersects(line) &&
				lineSegmentCrosses(line) &&
				line.lineSegmentCrosses(this);
	}
	
	
	/**
	 * Checks if the given line segment crosses this line segments's line
	 */
	private boolean lineSegmentCrosses(Line line){
		return start.subtract(line.start).crossProduct(line.end.subtract(line.start)) < 0 ^
				end.subtract(line.start).crossProduct(line.end.subtract(line.start)) < 0;
	}
	
	
	/**
	 * Checks if this line's bounding box and the given line's bounding box intersect
	 */
	private boolean boundingBoxIntersects(Line line){
		return start.getX() <= line.end.getX()
		        && end.getX() >= line.start.getX()
		        && start.getY() <= line.end.getY()
		        && end.getY() >= line.start.getY();
	}
}
