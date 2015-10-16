// ==========================================================================
// $Id: SegmentComparator.java 10 2010-12-15 04:53:00Z a.solis.m $
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
import java.util.Comparator;

import structures.Line.VERTICAL;

public class SegmentComparator implements Comparator<Segment> {
	
	VERTICAL _vline;
	private double P_ERROR = 0.0001;
	
	public SegmentComparator(VERTICAL v){
		_vline = v;
	}
	@Override
	public int compare(Segment arg0, Segment arg1) {
		//  1 if arg0  > arg1
		// -1 if arg0  < arg1
		//  0 if arg0 == arg1

		if (arg0.equals(arg1)) return 0;
		Segment vertical = new Segment(_vline.getX(),-2,_vline.getX(),-1);
		Point2D.Double p0 = arg0.Intersection(vertical);
		if (arg0.isVertical() && arg0.contains(_vline.getX(),_vline.getY())){
					p0 = new Point2D.Double(_vline.getX(),_vline.getY());
		}
		Point2D.Double p1 = arg1.Intersection(vertical);
		if (arg1.isVertical() && arg1.contains(_vline.getX(),_vline.getY())){
			p1 = new Point2D.Double(_vline.getX(),_vline.getY());
		}
		
		if (Math.abs(p0.y - p1.y)< P_ERROR  && Math.abs(p1.y - _vline.getY())< P_ERROR){
			if (arg0.isVertical()) return -1;
			if (arg1.isVertical()) return 1;
		}
		
		//if (p0.y < p1.y) return  1;
		if ( (p1.y - p0.y) > P_ERROR) return 1;
		//if (p0.y > p1.y) return -1;
		if ( (p0.y - p1.y) > P_ERROR) return -1;
		
		// they both pass the the vertical line to the right
		if (arg0.LAST().X() > _vline.getX() && arg1.LAST().X() > _vline.getX()) {
			return (Utils.LeftTurn(arg0.LAST()._p, p0, arg1.LAST()._p))?-1:1;
			//return  (arg0.LAST().Y() < arg1.LAST().Y() )?  1:  -1;
		}
		//they both end at the vertical line
		if (arg0.LAST().X() == _vline.getX() && arg1.LAST().X() == _vline.getX()){
			return (Utils.RightTurn(arg0.FIRST()._p, p0, arg1.FIRST()._p))?-1:1;
			//return  (arg0.FIRST().Y() < arg1.FIRST().Y() )? 1:  -1;
		} 
		//one end and the other passes the vertical line
		if (arg0.LAST().X() == _vline.getX() && arg1.LAST().X() > _vline.getX()){
			return ( arg1.FIRST().Y() >= p0.y )? 1: -1; 
		} else {
			return ( arg0.FIRST().Y() >= p1.y )? 1: -1; 
		}
	}

}
