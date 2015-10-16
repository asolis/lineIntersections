// ==========================================================================
// $Id: TimeBlinkingShape.java 10 2010-12-15 04:53:00Z a.solis.m $
// Blinking Shape, it uses time instead of amount of blinks.
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

public class TimeBlinkingShape extends BlinkingShape implements TemporalDrawableShape{

	private long milliseconds = 3000;
	public TimeBlinkingShape(Component canvas, Shape shape) {
		super(canvas, shape);
		// TODO Auto-generated constructor stub
	}
	
	public TimeBlinkingShape(Component canvas, Shape shape, long milliseconds) {
		super(canvas, shape);
		this.milliseconds = milliseconds;
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
	    while (blinker != null && ( milliseconds>0)) {
	    	repaintBounds();
		      
			      try {
			        Thread.sleep(laptime);
			      }
			      catch (InterruptedException e) {
			    	milliseconds = 0;
			        break;
			      }
		      visible = !visible;
		      milliseconds-=laptime;
	    }
	    if (milliseconds <=0) {
	    	stopAnimation();
	    	visible = false;
	    	repaintBounds();
	    }		
	}
		@Override
	public boolean Remove() {
		// TODO Auto-generated method stub
		return this.milliseconds <= 0;
	}
}
