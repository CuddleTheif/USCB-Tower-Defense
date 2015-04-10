package com.necrolore.menu;



import android.graphics.Typeface;

import com.necrolore.Level;
import com.necrolore.actor.Button;
import com.necrolore.actor.path.PathShape;
import com.necrolore.util.FontMetrics;
import com.necrolore.util.Point;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.awt.Color;
import greenfoot.awt.Font;

public class Menu extends World {
	
	private Button buttons[] = new Button[4];
	public static int WIDTH, HEIGHT, SCALE;

	public Menu() {
		
		super(WIDTH, HEIGHT, 1);
		GreenfootImage background = new GreenfootImage("Title.jpg");
		background.scale(WIDTH, HEIGHT);
		setBackground(background);
		getBackground().setColor(Color.WHITE);
		Font font = new Font(Typeface.DEFAULT_BOLD, SCALE/10);
		getBackground().setFont(font);
		FontMetrics fontMetrics = new FontMetrics(font);
		getBackground().drawString("USCB Tower Defense", getBackground().getWidth()/2-fontMetrics.getTextWidth("USCB Tower Defense")/2, fontMetrics.getTextHeight("USCB Tower Defense"));
		String text[] = {"Z Level", "Bolt Level", "S Level", "Help"};
		for(int i=0;i<buttons.length;i++){
			buttons[i] = new Button(WIDTH/2, HEIGHT/8, Color.BLACK, Color.WHITE, text[i]);
			addObject(buttons[i], getBackground().getWidth()/2, (int) (buttons[i].getImage().getHeight()*(i+0.5)+fontMetrics.getTextHeight("USCB Tower Defense")+(i+1)*HEIGHT/10));
		}
	}
	
	public static void reload(){
		Greenfoot.setWorld(new Menu());
	}
	
	public void act(){
		
		if(Greenfoot.mousePressed(buttons[0])){
			
			PathShape shapes[] = new PathShape[1];
			shapes[0]=PathShape.HORIZONTAL_Z;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(WIDTH-50, HEIGHT-50);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mousePressed(buttons[1])){
			

			PathShape shapes[] = new PathShape[1];
			shapes[0] =  PathShape.HORIZONTAL_BOLT;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(WIDTH-50, 50);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mousePressed(buttons[2])){

			PathShape shapes[] = new PathShape[1];
			shapes[0] = PathShape.VERTICAL_S;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(WIDTH-50, HEIGHT-50);
			Greenfoot.setWorld(new Level(WIDTH, HEIGHT,shapes, starts, ends));
			
		}else if(Greenfoot.mousePressed(buttons[3]))
			Greenfoot.setWorld(new HelpMenu(WIDTH, HEIGHT));
		
	}

}// End class menu 
