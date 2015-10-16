// ==========================================================================
// $Id: Animation.java 10 2010-12-15 04:53:00Z a.solis.m $
// Base class of all operations
// ==========================================================================
// (C)opyright:
//
//   Andres Solis Montero
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada.
//   http://www.site.uottawa.ca
//
// Creator: asolis (Andres Solis Montero)
// Email:   asoli094@uottawa.ca
// ==========================================================================
// $Rev: 10 $
// $LastChangedBy: a.solis.m $
// $LastChangedDate: 2010-12-14 23:53:00 -0500 (Tue, 14 Dec 2010) $
//
// ==========================================================================
package util.Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

public class Animation extends CountBlinkingShape implements  Animated{

	
	private LinkedList<DrawableShape> shapes;
	private boolean once = true;
	
	public Animation(Component canvas,LinkedList<DrawableShape> s)
	{
		super(canvas,s.get(0).shape);
		shapes = s;
		visible = false;
		count =2;
		laptime = 400;
	}
	public Animation(Component canvas, LinkedList<DrawableShape> s, int Count) {
		this(canvas, s);
		this.count = Count;
		// TODO Auto-generated constructor stub
	}
	@Override
	 protected void repaintBounds(){
	      canvas.repaint(0,0,canvas.getWidth(),canvas.getHeight());
	  }
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (visible){
		for (DrawableShape s: shapes)
		{
			if (visible)
			s.draw(g);
		}}
	}
	@Override
	public void startAnimation()
	{
		if (once){
			once = false;
		visible = true;
		super.startAnimation();
		}
	}
}
