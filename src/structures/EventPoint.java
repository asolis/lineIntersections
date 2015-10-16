// ==========================================================================
// $Id: EventPoint.java 10 2010-12-15 04:53:00Z a.solis.m $
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
package structures;

import java.awt.geom.Point2D;

public abstract class EventPoint implements Comparable<EventPoint> {

	protected Point2D.Double _p;
	protected Segment _seg1;
	
	protected EventPoint(double x, double y, Segment seg){
		_p = new Point2D.Double(x,y);
		_seg1 = seg;
	}
	
	public Segment getSegment(){
		return _seg1;
	}
	
	public double X() {
		// TODO Auto-generated method stub
		return _p.x;
	}

	
	public double Y() {
		// TODO Auto-generated method stub
		return _p.y;
	}
	
	public boolean isStartingPoint(){
		return (_seg1.FIRST().equals(this));
	}
	public boolean isEndingPoint(){
		return (_seg1.LAST().equals(this));
	}
	
	@Override
	public String toString(){
		return String.format("Point(%.2f,%.2f)",_p.x,_p.y);
	}
	@Override
	public int compareTo(EventPoint arg0) {
		// TODO Auto-generated method stub
		
		if (this.equals(arg0))return 0;
		else {
			if (_p.x < arg0.X()) return -1;
			if (_p.x == arg0.X() && _p.y < arg0.Y()) return -1;
			return 1;
		}
	}
	@Override
	public boolean equals(Object arg){
		return _p.equals(((EventPoint)arg)._p);
	}
}
