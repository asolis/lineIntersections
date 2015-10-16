// ==========================================================================
// $Id: IntersectionPoint.java 10 2010-12-15 04:53:00Z a.solis.m $
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


public class IntersectionPoint extends EventPoint {
	
	private Segment _seg2;	
	
	public Segment getSegment2(){
		return _seg2;
	}
	public IntersectionPoint(double x, double y, Segment seg,Segment  seg2){
		super(x,y,seg);
		_seg2 = seg2;
	}
	@Override
	public boolean isEndingPoint(){
		return (this.X() == _seg1.LAST().X()  && this.Y() == _seg1.LAST().Y())&&
				(this.X() == _seg2.LAST().X()  && this.Y() == _seg2.LAST().Y());
	}
	
	@Override
	public boolean isStartingPoint(){
		return (this.X() == _seg1.FIRST().X() && this.Y() == _seg1.FIRST().Y())&&
		       (this.X() == _seg2.FIRST().X() && this.Y() == _seg2.FIRST().Y());
	}
	
	public boolean isEndPointSeg(){
		return (this.X() == _seg1.FIRST().X() && this.Y() == _seg1.FIRST().Y())||
			   (this.X() == _seg1.LAST().X()  && this.Y() == _seg1.LAST().Y()) ;
	}

	public boolean isEndPointSeg2()
	{
		return (this.X() == _seg2.FIRST().X() && this.Y() == _seg2.FIRST().Y())||
		       (this.X() == _seg2.LAST().X()  && this.Y() == _seg2.LAST().Y());
	}
}
