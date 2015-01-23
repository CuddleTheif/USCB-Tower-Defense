package com.necrolore.greenfoot;

import java.awt.Color;

import com.necrolore.menu.Button;

import greenfoot.Greenfoot;
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
		
	}// End method act
	
	
	/**
	 * Updates the page to the current page number
	 */
	private void updatePage(){
		
		/* Clear the current menu */
			getBackground().clear();
			removeObjects(getObjects(Button.class));
			addButton(backButton, 1);
		
		/* Find out which page the menu is on */
			switch(page){
			
			case 0:
				addButton(nextButton, 0);
				break;
				
			case 1:
				addButton(nextButton, 0);
				addButton(prevButton, 2);
				break;
				
			case 2:
				addButton(nextButton, 0);
				addButton(prevButton, 2);
				break;
				
			case 3:
				addButton(nextButton, 0);
				addButton(prevButton, 2);
				break;
				
			case 4:
				addButton(prevButton, 2);
				break;
			
			}// End switch(page)
		
	}// End method updatePage
	
	
	/**
	 * Add the given button of the given number
	 * 
	 * @param button   The button to add
	 * @param num      The number of the button (0, 1, or 2)
	 */
	private void addButton(Button button, int num){
		
		/* Add the button given */
			addObject(button, getBackground().getWidth()*(1+5*num)/16, getBackground().getHeight()-button.getImage().getHeight());
		
	}// End method add Button

}// End class HelpMenu
