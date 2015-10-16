// ==========================================================================
// $Id: DrawableShape.java 10 2010-12-15 04:53:00Z a.solis.m $
// Base class of Drawable Shapes(java.awt.geom)
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
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class DrawableShape implements Drawable {
	
	  protected Component canvas;
	  protected Shape     shape;
	  protected boolean  visible  = true;
	  protected Color color = Color.black;
	  protected BasicStroke stroke = new BasicStroke(1);
	  public boolean fillable = false;
	  
	  protected double scale = 1;
	  
	  public void setStroke(BasicStroke s)
	  {
		  stroke = s;
	  }
	  public BasicStroke getStroke(){
		  return stroke;
	  }
	  public void setColor(int argb){
		  color = new Color(argb);
	  }
	  public void setColor(Color c){
		  color = c;
	  }
	  
	  public Color getColor()
	  {
		  return color;
	  }
	  public void setScale(double scale){
		  this.scale = scale;
	  }
	  public DrawableShape(Component canvas, Shape shape)
	  {
		  this.canvas = canvas;
		  this.shape = shape;
	  }
	  
	  public void draw(Graphics2D g) {
		  Color bC  = g.getColor();
		  Stroke bS = g.getStroke();
		  g.setStroke(stroke);
		  g.setColor (color);
		  if (visible){
			  if (fillable) fillShape(g);
			  else  		drawShape(g);
     	  }
		  g.setStroke(bS);
		  g.setColor(bC);
		  
	  }
	  
	  public void fillShape(Graphics2D g){
		  g.fill(translateShape(shape,scale));
	  }
	  
	  public void drawShape(Graphics2D g){
		  g.draw(translateShape(shape,scale));		  
	  }
	  
	  
	  public static Shape translateShape(final Shape shape, double scale) 
	  {
		  if (shape == null) {
			throw new IllegalArgumentException("Null 'shape' argument.");
		  }
		  
		  Rectangle2D r = shape.getBounds2D();
		  double transX = r.getX()*scale - r.getX();
		  double transY = r.getY()*scale - r.getY();
		  final AffineTransform transform = 
			  		AffineTransform.getTranslateInstance(transX, transY);
			return transform.createTransformedShape(shape);
	  }

	  
}
