package com.necrolore.menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import com.necrolore.actor.Button;
import com.necrolore.actor.entity.Attribute;
import com.necrolore.actor.entity.tower.ComputationalScience;
import com.necrolore.actor.entity.tower.Nursing;

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
			text[0] = new String[]{"The Goal", "stop the bees from reaching the USCB logo for", "as long as possible by placing and upgrading", "towers along the path to kill them"};
			text[1] = new String[]{"The Towers", 
									"Computational Science: A tower the produces \"robots\"", "that fight bees until they die", "",
									"Nursing: A tower that shoots syringes at the bees", "to hurt them"};
			
			
		/* Assign and/or get all the images for the pages */
			images[0] = new GreenfootImage[0];
			images[1] = new GreenfootImage[]{ComputationalScience.getBaseImage(), null, null, Nursing.getBaseImage()};
			images[1][0].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
			images[1][3].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
			
			
		/* Get the images and text for the upgrade page */
			text[2] = new String[Attribute.upgradeValues().length*2];
			text[2][0] = "Upgrades";
			images[2] = new GreenfootImage[Attribute.upgradeValues().length*2];
			for(int i=0;i<Attribute.upgradeValues().length;i++){
				text[2][i*2+1] = Attribute.upgradeValues()[i].getDescription();
				text[2][i*2+2==text[2].length ? 2 : i*2+2] = "";
				images[2][i*2] = Attribute.upgradeValues()[i].getImage();
				images[2][i*2].scale(getBackground().getWidth()/20, getBackground().getHeight()/10);
				images[2][i*2+1==text[2].length ? 1 : i*2+1] = null;
			}// End for(int i=0;i<text[2].length;i++)
		
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
			Font font = new Font(Font.MONOSPACED, Font.BOLD, getBackground().getWidth()*getBackground().getHeight()/8000);
			FontMetrics fontMetrics = getBackground().getAwtImage().getGraphics().getFontMetrics(font);
			getBackground().setColor(Color.WHITE);
			getBackground().setFont(font);
			getBackground().drawString(text[page][0], 
					getBackground().getWidth()/2-fontMetrics.stringWidth(text[page][0])/2, fontMetrics.getHeight()/2);
			
			
		/* Draw the text on the current page */
			font = new Font(Font.MONOSPACED, Font.BOLD, getBackground().getWidth()*getBackground().getHeight()/16000);
			fontMetrics = getBackground().getAwtImage().getGraphics().getFontMetrics(font);
			getBackground().setFont(font);
			for(int line=1;line<text[page].length;line++)
				getBackground().drawString(text[page][line], 
						getBackground().getWidth()/2-fontMetrics.stringWidth(text[page][line])/2, getBackground().getHeight()/2-fontMetrics.getHeight()*(text[page].length/2-line));
			
			
		/* Draw the images on the current page */
			for(int line=0;line<images[page].length;line++)
				if(images[page][line]!=null)
					getBackground().drawImage(images[page][line], 0, getBackground().getHeight()/2-fontMetrics.getHeight()*(text[page].length/2-line));
			
			
			
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
