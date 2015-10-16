// ==========================================================================
// $Id: CountBlinkingShape.java 10 2010-12-15 04:53:00Z a.solis.m $
// Temporal Blinking Shapes, only blinked an amount of times
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

import java.awt.Component;
import java.awt.Shape;

public class CountBlinkingShape extends BlinkingShape implements TemporalDrawableShape{

	protected int count = 5;
	
	
	public CountBlinkingShape(Component canvas, Shape shape) {
		super(canvas, shape);
		// TODO Auto-generated constructor stub
	}
	public CountBlinkingShape(Component canvas, Shape shape, int Count) {
		super(canvas, shape);
		this.count = Count;
		// TODO Auto-generated constructor stub
	}
	public void run () {
		    while (blinker != null && (count>0)) {
		    	repaintBounds();
		      
		      try {
		        Thread.sleep(laptime);
		      }
		      catch (InterruptedException e) {
		    	count = 0;
		        break;
		      }
		      visible = !visible;
		      if (visible) count--;
		    }
		    if (count <=0) {
		    	stopAnimation();
		    	visible = false;
		    	repaintBounds();
		    }
	}
	
	@Override
	public boolean Remove() {
		// TODO Auto-generated method stub
		return count<=0;
	}
	
	
	

}
