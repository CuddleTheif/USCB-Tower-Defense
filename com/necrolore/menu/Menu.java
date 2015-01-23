package com.necrolore.menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Arrays;

import com.necrolore.greenfoot.HelpMenu;
import com.necrolore.level.Level;
import com.necrolore.road.PathShape;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Menu extends World {
	
	private Button buttons[] = new Button[4];
	public static int WIDTH = 600;
	public static int HEIGHT = 400;

	public Menu() {
		
		super(600, 400, 1);
		
		setBackground("images/Title.jpg");
		getBackground().setColor(Color.WHITE);
		getBackground().setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
		getBackground().drawString("USCB Tower Defense", getBackground().getWidth()/4, getBackground().getHeight()/16);
		String text[] = {"Z Level", "Bolt Level", "S Level", "Help"};
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new Button(WIDTH/2, HEIGHT/8, Color.BLACK, Color.WHITE, text[i]);
			addObject(buttons[i], getBackground().getWidth()/2, getBackground().getHeight()*(1+i)*3/16);
		}
	}
	
	public void act(){
		
		if(Greenfoot.mouseClicked(buttons[0])){
			
			PathShape shapes[] = new PathShape[1];
			shapes[0]=PathShape.HORIZONTAL_Z;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 350);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mouseClicked(buttons[1])){
			

			PathShape shapes[] = new PathShape[1];
			shapes[0] =  PathShape.HORIZONTAL_BOLT;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 50);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mouseClicked(buttons[2])){

			PathShape shapes[] = new PathShape[1];
			shapes[0] = PathShape.VERTICAL_S;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 350);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mouseClicked(buttons[3]))
			Greenfoot.setWorld(new HelpMenu(WIDTH, HEIGHT));
		
	}

}// End class menu 
