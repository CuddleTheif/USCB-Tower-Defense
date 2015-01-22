package com.necrolore.menu;

import greenfoot.GreenfootImage;

import java.awt.Color;

import com.necrolore.greenfoot.Actors;



public class Button extends Actors {

	
	/**
	 * Creates a button for a menu
	 * 
	 * @param width   The width of the window of the menu
	 * @param height  The height of the window of the menu
	 */
	public Button(int width, int height){
		
		super();
		setImage(new GreenfootImage(width/2, height/8));
		getImage().setColor(Color.BLACK);
		getImage().fillRect(0,0,width/2, height/8);
		
	}
	
	public void act() {
		// TODO Auto-generated method stub

	}

}
