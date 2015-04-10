package com.necrolore.menu;


import android.graphics.Typeface;

import com.necrolore.actor.Button;
import com.necrolore.actor.entity.Attribute;
import com.necrolore.actor.entity.tower.ComputationalScience;
import com.necrolore.actor.entity.tower.Nursing;
import com.necrolore.util.FontMetrics;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.awt.Color;
import greenfoot.awt.Font;

/**
 * A menu that shows how to play the game.
 * 
 * @author NecroTheif
 * @version 2015.01.23
 */
public class HelpMenu extends World {
	
	private int page; // The page the help menu is on
	private Button nextButton, prevButton; // The next and previous page buttons
	private Button backButton; // The button to go back to the main menu
	private String text[][] = new String[3][]; // The text on all the pages
	private GreenfootImage images[][] = new GreenfootImage[3][]; // The images on all the pages

	/**
	 * Creates a help menu of the given size
	 * 
	 * @param width    The width of the help menu
	 * @param height   The height of the help menu
	 */
	public HelpMenu(int width, int height) {
		
		/* Call super class' constructor to create the world */
			super(width, height, 1);
			
			
		/* Set the background to black, create the next, previous, and back buttons, and set the page to just created */
			getBackground().setColor(Color.BLACK);
			getBackground().fillRect(0, 0, width, height);
			nextButton = new Button(width/4, height/8, Color.BLACK, Color.WHITE, "Next");
			prevButton = new Button(width/4, height/8, Color.BLACK, Color.WHITE, "Previous");
			backButton = new Button(width/4, height/8, Color.BLACK, Color.WHITE, "Back");
			page = -1;
			
			
		/* Assign and/or get all the text for the pages */
			text[0] = new String[]{"The Goal", "stop the bees from reaching the USCB", "logo for as long as possible", " by placing and upgrading towers along the", " path to kill them"};
			text[1] = new String[]{"The Towers", 
									"Computational Science: A tower the produces", "\"robots\" that fight bees until they die", "",
									"Nursing: A tower that shoots syringes at the", "bees to hurt them"};
			
			
		/* Assign and/or get all the images for the pages */
			images[0] = new GreenfootImage[0];
			images[1] = new GreenfootImage[]{null, ComputationalScience.getBaseImage(), null, Nursing.getBaseImage()};
			images[1][1].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
			images[1][3].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
			
			
		/* Get the images and text for the upgrade page */
			text[2] = new String[Attribute.upgradeValues().length];
			text[2][0] = "Upgrades";
			images[2] = new GreenfootImage[Attribute.upgradeValues().length];
			for(int i=1;i<Attribute.upgradeValues().length;i++){
				text[2][i] = Attribute.upgradeValues()[i].getDescription();
				images[2][i] = Attribute.upgradeValues()[i].getImage();
				images[2][i].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
			}// End for(int i=0;i<text[2].length;i++)
		
	}// End two-argument constructor for HelpMenu
	
	
	/**
	 * Checks if a button was clicked and sets the new page
	 */
	public void act(){
		
		/* Check if the menu was just created or the user clicked a button */
		if(Greenfoot.mousePressed(nextButton) || page==-1){
			
			/* Add 1 to the page and update the page */
				page++;
				updatePage();
			
		}// End if(Greenfoot.mouseClicked(nextButton) || page==-1)
		else if(Greenfoot.mousePressed(prevButton)){
			
			/* Subtract 1 from the page and update the page */
				page--;
				updatePage();
			
		}// End else if(Greenfoot.mouseClicked(prevButton))
		else if(Greenfoot.mousePressed(backButton))
			Greenfoot.setWorld(new Menu());
		
	}// End method act
	
	
	/**
	 * Updates the page to the current page number
	 */
	private void updatePage(){
		
		/* Clear the current menu */
			getBackground().clear();
			getBackground().setColor(Color.BLACK);
			getBackground().fillRect(0, 0, getBackground().getWidth(), getBackground().getHeight());
			removeObjects(getObjects(Button.class));
			
			
		/* Draw the title of the page */
			Font font = new Font(Typeface.DEFAULT_BOLD, Menu.SCALE/10);
			FontMetrics fontMetrics = new FontMetrics(font);
			getBackground().setColor(Color.WHITE);
			getBackground().setFont(font);
			getBackground().drawString(text[page][0], 
					getBackground().getWidth()/2-fontMetrics.getTextWidth(text[page][0])/2, fontMetrics.getTextHeight(text[page][0]));
			
			
		/* Draw the text and images on the current page */
			font = new Font(Typeface.DEFAULT, Menu.SCALE/20);
			fontMetrics.setFont(font);
			getBackground().setFont(font);
			int drawHeight = Menu.HEIGHT/2-(fontMetrics.getTextHeight(text[page][0])*text[page].length+getBackground().getHeight()/10*(images[page].length-1))/2;
			for(int line=2;line<text[page].length*2;line++){
				
				if(line % 2 == 0){
					if(images[page].length > line/2 && images[page][line/2]!=null){
						getBackground().drawImage(images[page][line/2], getBackground().getWidth()/2-images[page][line/2].getWidth()/2, drawHeight);
						drawHeight+=getBackground().getHeight()/10;
					}
				}
				else{
					getBackground().drawString(text[page][line/2], 
							getBackground().getWidth()/2-fontMetrics.getTextWidth(text[page][line/2])/2, drawHeight+fontMetrics.getTextHeight(text[page][line/2])/2);
					drawHeight+=fontMetrics.getTextHeight(text[page][line/2]);
				}
			}
			
			
		/* Draw the buttons on the page */
			addButton(backButton, 2);
			if(page<text.length-1)addButton(nextButton, 3);
			if(page>0)addButton(prevButton, 1);
		
	}// End method updatePage
	
	
	/**
	 * Add the given button of the given number
	 * 
	 * @param button   The button to add
	 * @param num      The number of the button (1, 2, or 3)
	 */
	private void addButton(Button button, int num){
		
		/* Add the button given */
			addObject(button, getBackground().getWidth()*num/4, getBackground().getHeight()-button.getImage().getHeight());
		
	}// End method add Button

}// End class HelpMenu
