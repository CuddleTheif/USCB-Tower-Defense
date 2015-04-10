package com.necrolore;

import greenfoot.GreenfootVisitor;

import org.droidfoot.DroidfootActivity;
import org.droidfoot.Settings;
import org.droidfoot.gui.DrawPanel;
import org.droidfoot.simulation.SimulationManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.necrolore.menu.Menu;

public class DFTowerDefenseActivity extends DroidfootActivity {

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Point screenSize = new Point();
		this.getWindowManager().getDefaultDisplay().getSize(screenSize);
		Menu.WIDTH = Integer.valueOf(screenSize.x);
		Menu.HEIGHT = Integer.valueOf(screenSize.y);
		Menu.SCALE = Menu.WIDTH > Menu.HEIGHT ? Menu.HEIGHT : Menu.WIDTH;
		
		DroidfootActivity.worldClass = Menu.class;

		// help item available in the menu (true/false)?
		Settings.help = false;

		// speed item and speed slider available in the action bar (true/false)?
		Settings.speed = false;
		
		// background color of the droidfoot activity view
		Settings.backgroundColor = Color.rgb(0, 0, 0);

		// scale option available in the settings (true/false
		Settings.scale = false;
		
		// if true the world will always cover the whole screen
		Settings.scaleDefault = false;

		// orientation option available in the settings (true/false)?
		Settings.orientation = false;
		// if LANDSCAPE always landscape modus
		// if PORTRAIT always portrait modus
		// if SWITCH automatic change between portrait and landscape modus due
		// to device rotations
		Settings.orientationDefault = Settings.LANDSCAPE; // LANDSCAPE, PORTRAIT,
														// SWITCH

		// username option available in the settings (true/false)?
		Settings.username = false;
		// name for the highscore table
		Settings.usernameDefault = "Default";
		
		super.onCreate(savedInstanceState);
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
			DrawPanel.canvas.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_FULLSCREEN);
		else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			DrawPanel.canvas.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_FULLSCREEN);
		else
			DrawPanel.canvas.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		SimulationManager.getManager().setGFSpeed(GreenfootVisitor.MAX_SIMULATION_SPEED);
		SimulationManager.getManager().start();
		Menu.reload();
	}

}
