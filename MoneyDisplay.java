

import java.awt.Color;
import java.awt.Font;

import greenfoot.GreenfootImage;

public class MoneyDisplay extends Actors {

	
	/**
	 * Creates a MoneyDisplay
	 */
	public MoneyDisplay(){
		
		/* Set the size of the money display depending on the level size */
			setImage(new GreenfootImage(Level.WORLD_WIDTH/10, Level.WORLD_HEIGHT/20));
		
	}// End zero-argument constructor
	
	
	public void act() {
		
		/* Draw a black rectangle for the background */
			getImage().clear();
			getImage().setColor(Color.BLACK);
			getImage().fillRect(0, 0, getImage().getWidth(), getImage().getHeight());
		
			
		/* Draw a coin on the money display */
			GreenfootImage coin = new GreenfootImage("images/coin.png");
			coin.scale(Tower.WIDTH/2, Tower.WIDTH/2);
			getImage().drawImage(coin, 0, 0);
		
			
		/* Update the money value to the current gold count */
			String gold = String.valueOf(((Level)getWorld()).getGold());
			getImage().setColor(Color.WHITE);
			getImage().setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			getImage().drawString(gold, Tower.WIDTH/2, getImage().getHeight()*2/3);
		
	}// End method act

}// End class MoneyDisplay