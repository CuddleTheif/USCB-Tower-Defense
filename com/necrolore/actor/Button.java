package com.necrolore.actor;


import greenfoot.GreenfootImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;



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
		Font font = new Font(Font.MONOSPACED, Font.BOLD, width*height/500);
		FontMetrics fontMetrics = getImage().getAwtImage().getGraphics().getFontMetrics(font);
		getImage().setFont(font);
		getImage().drawString(text, width/2-fontMetrics.stringWidth(text)/2, height/2+fontMetrics.getHeight()/4);
		
	}
	
	public void act() {
		// TODO Auto-generated method stub

	}

}
