package com.necrolore.actor;


import android.graphics.Typeface;

import com.necrolore.menu.Menu;
import com.necrolore.util.FontMetrics;

import greenfoot.GreenfootImage;
import greenfoot.awt.Color;
import greenfoot.awt.Font;



public class Button extends Actors {

	
	/**
	 * Creates a button for a menu
	 * 
	 * @param width        The width of the button
	 * @param height       The height of the button
	 * @param background   The color of the background in the button
	 * @param foreground   The color of the foreground in the button
	 * @param text         The text on the button
	 */
	public Button(int width, int height, Color background, Color foreground, String text){
		
		super();
		setImage(new GreenfootImage(width, height));
		getImage().setColor(background);
		getImage().fillRect(0,0,width, height);
		getImage().setColor(Color.WHITE);
		Font font = new Font(Typeface.DEFAULT_BOLD, Menu.SCALE/15);
		FontMetrics fontMetrics = new FontMetrics(font);
		getImage().setFont(font);
		getImage().drawString(text, width/2-fontMetrics.getTextWidth(text)/2, height/2+fontMetrics.getTextHeight(text)/4);
		
	}
	
	public void act() {

	}

}
