package com.necrolore.greenfoot;

import java.awt.Color;
import greenfoot.GreenfootImage;

public class Range extends Actors {

	
	/**
	 * Sets the range's range to the given value
	 * 
	 * @param range   The new range
	 */
	public void setRange(int range){
		
		/* Create a image with the given range of a transparent oval */
			setImage(new GreenfootImage(2*range,2*range));
			getImage().setColor(new Color(0,0,0,100));
			getImage().fillOval(0, 0, 2*range, 2*range);
		
	}// End method setRange
	
	
	
	public void act() {
		// TODO Auto-generated method stub
	}
}
