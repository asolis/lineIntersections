// ==========================================================================
// $Id: BlinkingShape.java 10 2010-12-15 04:53:00Z a.solis.m $
// Blinking Shapes objects
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

import java.awt.*;

import util.Graphics.Animated;
import util.Graphics.DrawableShape;




public class BlinkingShape extends DrawableShape implements Animated {
	

  protected int      laptime = 500;
  protected Thread   blinker  = null;
  
  public BlinkingShape(Component canvas, Shape shape)
  {
	  super(canvas,shape);
  }
  
  protected void repaintBounds(){
      Rectangle box = shape.getBounds();
      int offset = (int) Math.ceil(stroke.getLineWidth());
      Rectangle r =  super.translateShape(box, scale).getBounds();
      canvas.repaint(r.x - offset ,r.y - offset , r.width+ offset*2  , r.height+ offset*2 );
  }
  public void run () {
    while (blinker != null) {
    	repaintBounds();
      try {
        Thread.sleep(laptime);
      }
      catch (InterruptedException e) {
        break;
      }
      visible = !visible;
    }
  }

  public void startAnimation () {
    if ( blinker == null) {
      blinker = new Thread(this);
      blinker.start();
    }
  }

  public void stopAnimation () {
    blinker = null;
    visible = true;
  }

  public int getLaptime () {
    return laptime;
  }

  public void setLaptime (int milliseconds) {
    laptime = milliseconds;
  }
}

