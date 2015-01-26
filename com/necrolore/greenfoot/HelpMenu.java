package com.necrolore.greenfoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

import com.necrolore.menu.Button;
import com.necrolore.menu.Menu;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

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
	private String text[][] = {{"The Goal", "stop 10 bees from reaching", "the USCB logo for as", "long as possible"},
								{"The Towers", "Computational Science: A tower the produces \"robots\" that fight bees until they die"},
								{"page 3"},
								{"page 4"},
								{"page 5"}}; // The text on all the pages
	private GreenfootImage images[][] = {{},
										 {},
										 {},
										 {}}; // The images on all the pages
	}

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
		
	}// End two-argument constructor for HelpMenu
	
	
	/**
	 * Checks if a button was clicked and sets the new page
	 */
	public void act(){
		
		/* Check if the menu was just created or the user clicked a button */
		if(Greenfoot.mouseClicked(nextButton) || page==-1){
			
			/* Add 1 to the page and update the page */
				page++;
				updatePage();
			
		}// End if(Greenfoot.mouseClicked(nextButton) || page==-1)
		else if(Greenfoot.mouseClicked(prevButton)){
			
			/* Subtract 1 from the page and update the page */
				page--;
				updatePage();
			
		}// End else if(Greenfoot.mouseClicked(prevButton))
		else if(Greenfoot.mouseClicked(backButton))
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
			Font font = new Font(Font.MONOSPACED, Font.BOLD, getBackground().getWidth()*getBackground().getHeight()/4000);
			FontMetrics fontMetrics = getBackground().getAwtImage().getGraphics().getFontMetrics(font);
			getBackground().setColor(Color.WHITE);
			getBackground().setFont(font);
			getBackground().drawString(pages[page][0], 
					getBackground().getWidth()/2-fontMetrics.stringWidth(pages[page][0])/2, fontMetrics.getHeight()/2);
			
			
		/* Draw the  text on the current page */
			font = new Font(Font.MONOSPACED, Font.BOLD, getBackground().getWidth()*getBackground().getHeight()/8000);
			fontMetrics = getBackground().getAwtImage().getGraphics().getFontMetrics(font);
			getBackground().setFont(font);
			for(int line=1;line<pages[page].length;line++)
				getBackground().drawString(pages[page][line], 
						getBackground().getWidth()/2-fontMetrics.stringWidth(pages[page][line])/2, getBackground().getHeight()/2-fontMetrics.getHeight()*(pages[page].length-line-1));
			
			
		/* Draw the buttons on the page */
			addButton(backButton, 2);
			if(page<4)addButton(nextButton, 3);
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
