package com.necrolore.util;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import greenfoot.awt.Font;

public class FontMetrics {
	
	private Paint paint;
	
	/**
	 * Creates a font metrics based on the default font
	 */
	public FontMetrics(){
		this(new Font());
	}
	
	/**
	 * Creates a font metrics based on the given font
	 */
	public FontMetrics(Font font){
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(1f);
		paint.setTypeface(font.getTypeface());
		paint.setTextAlign(Align.LEFT);
		if(font.getSize()!=-1)
			paint.setTextSize(font.getSize());
	}
	
	
	/**
	 * Set the font for this font metrics
	 */
	public void setFont(Font font){
		paint.setTypeface(font.getTypeface());
		paint.setTextSize(font.getSize());
	}
	
	
	/**
	 * Get the height of the given text
	 */
	public int getTextHeight(String text){
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	
	
	/**
	 * Get the width of the given text
	 */
	public int getTextWidth(String text){
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.width();
	}
	
	
	/**
	 * Gets the default text size for the given type face
	 */
	public static float getDefaultSize(Typeface type){
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(1f);
		paint.setTypeface(type);
		paint.setTextAlign(Align.LEFT);
		return paint.getTextSize();
	}
	
}
