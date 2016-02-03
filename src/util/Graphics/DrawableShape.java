// ==========================================================================
// $Id: DrawableShape.java 10 2010-12-15 04:53:00Z a.solis.m $
// Base class of Drawable Shapes(java.awt.geom)
/**************************************************************************************************
 **************************************************************************************************
 
     BSD 3-Clause License (https://www.tldrlegal.com/l/bsd3)
     
     Copyright (c) 2010 Andrés Solís Montero <http://www.solism.ca>, All rights reserved.
     
     
     Redistribution and use in source and binary forms, with or without modification,
     are permitted provided that the following conditions are met:
     
     1. Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
     2. Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the documentation
        and/or other materials provided with the distribution.
     3. Neither the name of the copyright holder nor the names of its contributors
        may be used to endorse or promote products derived from this software
        without specific prior written permission.
     
     THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
     AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
     IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
     ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
     LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
     DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
     LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
     THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
     OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
     OF THE POSSIBILITY OF SUCH DAMAGE.
 
 **************************************************************************************************
 **************************************************************************************************/
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
