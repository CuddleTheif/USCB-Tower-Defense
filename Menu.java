

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Arrays;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Menu extends World {
	
	private Button buttons[] = new Button[3];

	public Menu() {
		
		super(600, 400, 1);
		
		setBackground("images/Title.jpg");
		getBackground().setColor(Color.WHITE);
		getBackground().setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
		getBackground().drawString("USCB Tower Defense", getBackground().getWidth()/4, getBackground().getHeight()/4);
		buttons[0] = new Button();
		buttons[0].setImage(new GreenfootImage(getBackground().getWidth()/2, getBackground().getHeight()/8));
		buttons[0].getImage().setColor(Color.BLACK);
		buttons[0].getImage().fillRect(0,0,getBackground().getWidth()/2, getBackground().getHeight()/8);
		this.addObject(buttons[0], getBackground().getWidth()/2, getBackground().getHeight()*5/16);
		buttons[1] = new Button();
		buttons[1].setImage(new GreenfootImage(getBackground().getWidth()/2, getBackground().getHeight()/8));
		buttons[1].getImage().setColor(Color.BLACK);
		buttons[1].getImage().fillRect(0,0, getBackground().getWidth()/2, getBackground().getHeight()/8);
		addObject(buttons[1], getBackground().getWidth()/2, getBackground().getHeight()/2);
		buttons[2] = new Button();
		buttons[2].setImage(new GreenfootImage(getBackground().getWidth()/2, getBackground().getHeight()/8));
		buttons[2].getImage().setColor(Color.BLACK);
		buttons[2].getImage().fillRect(0,0, getBackground().getWidth()/2, getBackground().getHeight()/8);
		addObject(buttons[2], getBackground().getWidth()/2, getBackground().getHeight()*11/16);
		buttons[0].getImage().setColor(Color.WHITE);
		buttons[0].getImage().drawString("Z shaped", 0,0);
		buttons[1].getImage().setColor(Color.WHITE);
		buttons[1].getImage().drawString("Bolt shaped", 0,0);
		buttons[2].getImage().setColor(Color.WHITE);
		buttons[2].getImage().drawString("Muti shaped", 0,0);
	}
	
	public void act(){
		
		if(Greenfoot.mouseClicked(buttons[0])){
			
			PathShape shapes[] = new PathShape[1];
			shapes[0]=PathShape.HORIZONTAL_Z;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 350);
			Greenfoot.setWorld(new Level(shapes, starts, ends));
			
		}else if(Greenfoot.mouseClicked(buttons[1])){
			

			PathShape shapes[] = new PathShape[1];
			shapes[0] =  PathShape.HORIZONTAL_BOLT;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 50);
			Greenfoot.setWorld(new Level(shapes, starts, ends));
			
		}else if(Greenfoot.mouseClicked(buttons[2])){

			PathShape shapes[] = new PathShape[1];
			shapes[2] = PathShape.VERTICAL_S;
			Point starts[] = new Point[1];
			starts[0] = new Point(50, 50);
			Point ends[] = new Point[1];
			ends[0] = new Point(550, 350);
			Greenfoot.setWorld(new Level(shapes, starts, ends));
		}
		
	}

}// End class menu 
