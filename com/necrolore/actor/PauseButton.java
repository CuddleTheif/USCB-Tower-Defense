package com.necrolore.actor;



import java.awt.Color;
import java.util.List;

import com.necrolore.actor.entity.Entity;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class PauseButton extends Actors {
	
	private boolean paused; // If the world is paused or not
	
	/**
	 * Creates a pause button based on the given world sizes
	 * 
	 * @param worldWidth    The width of the world
	 * @param worldHeight   The height of the world
	 */
	public PauseButton(int worldWidth, int worldHeight){
		
		/* Create the pause buttons image based on the world's dimensions */
			setImage(new GreenfootImage(worldWidth/40, worldHeight/20));
			getImage().setColor(Color.WHITE);
			
			
	}// End two-argument constructor for PauseButton
	
	private void drawPaused(){
		
		/* Clear the image */
			getImage().clear();
			
			
		/* Draw triangle for play button */
			int width = getImage().getWidth();
			int height = getImage().getHeight();
			getImage().fillPolygon(new int[]{0, 0, width}, new int[]{height, 0, height/2}, 3);
		
	}// End method drawPaused
	
	
	private void drawUnpaused(){
		
		/* Clear the image */
			getImage().clear();
		
			
		/* Draw two rectangles for pause button */
			int width = getImage().getWidth();
			int height = getImage().getHeight();
			getImage().fillRect(0, 0, width*3/7, height);
			getImage().fillRect(width*4/7, 0, width*3/7, height);
			
	}// End method drawUnpaused
	
	
	public void act() {
		
		/* Draw the current state of the pause button */
			if(paused){
				
				drawPaused();
				
			}// End if(paused)
			else{
				
				drawUnpaused();
				
			}// End else for if(paused)

		/* Check to see if this was clicked */
			if(Greenfoot.mouseClicked(this)){
				
				/* Get all the entities in the world */
					@SuppressWarnings("unchecked")
					List<Entity> entities = getWorld().getObjects(Entity.class);
					
					
				/* Determine if the game is paused yet or not */
					if(paused){
						
						/* Unpause each entity */
							for(Entity entity : entities){
								
								/* Unpause the current entity */
									entity.unpause();
								
							}// End for(Entity entity : entities)
						
							
						/* Set the world to not paused */
							paused = false;
							
					}// End if(paused)
					else{
						
						/* Pause each entity */
							for(Entity entity : entities){
								
								/* Pause the current entity */
									entity.pause();
								
							}// End for(Entity entity : entities)
						

						/* Set the world to paused */
							paused = true;
							
					}// End else for if(paused)
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act

}// End class PauseButton
