package com.necrolore.menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Arrays;

import com.necrolore.level.Level;
import com.necrolore.road.PathShape;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Menu extends World {
	
	private Button buttons[] = new Button[4];
	private static int WIDTH = 600;
	private static int HEIGHT = 400;

	public Menu() {
		
		super(600, 400, 1);
		
		setBackground("images/Title.jpg");
		getBackground().setColor(Color.WHITE);
		getBackground().setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
		getBackground().drawString("USCB Tower Defense", getBackground().getWidth()/4, getBackground().getHeight()/16);
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new Button(WIDTH, HEIGHT);
		}
		
		buttons[1] = new Button(WIDTH, HEIGHT);
		buttons[2] = new Button(WIDTH, HEIGHT);
		buttons[3] = new Button(WIDTH, HEIGHT);
		this.addObject(buttons[0], getBackground().getWidth()/2, getBackground().getHeight()*3/16);
		addObject(buttons[1], getBackground().getWidth()/2, getBackground().getHeight()*6/16);
		addObject(buttons[2], getBackground().getWidth()/2, getBackground().getHeight()*9/16);
		addObject(buttons[3], getBackground().getWidth()/2, getBackground().getHeight()*12/16);
		buttons[0].getImage().setColor(Color.WHITE);
		buttons[0].getImage().setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		buttons[1].getImage().setColor(Color.WHITE);
		buttons[1].getImage().setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		buttons[2].getImage().setColor(Color.WHITE);
		buttons[2].getImage().setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		buttons[2].getImage().setColor(Color.WHITE);
		buttons[2].getImage().setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		buttons[2].getImage().drawString("S shaped", buttons[2].getImage().getWidth()/3, buttons[2].getImage().getHeight()/2);
		buttons[0].getImage().drawString("Z shaped", buttons[2].getImage().getWidth()/3, buttons[0].getImage().getHeight()/2);
		buttons[2].getImage().drawString("S shaped", buttons[2].getImage().getWidth()/3, buttons[2].getImage().getHeight()/2);
		buttons[1].getImage().drawString("Bolt shaped", buttons[2].getImage().getWidth()/3, buttons[1].getImage().getHeight()/2);
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
		}
		
	}

}// End class menu 
