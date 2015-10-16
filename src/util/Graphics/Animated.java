// ==========================================================================
// $Id: Animated.java 10 2010-12-15 04:53:00Z a.solis.m $
// Interface for Animated Shapes
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

public interface Animated extends Runnable {

	  public void  startAnimation();

	  public void  stopAnimation();

	  public void  setLaptime(int r);

	  public int getLaptime();  
	}
