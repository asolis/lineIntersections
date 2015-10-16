// ==========================================================================
// $Id: EventPointList.java 10 2010-12-15 04:53:00Z a.solis.m $
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

import java.util.LinkedList;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

import structures.Line.VERTICAL;
public class EventPointList {

	public TreeSet<Segment> C;
	public TreeSet<Segment> U;
	public TreeSet<Segment> L;
	
	public LinkedList<EventPoint> events;
	private VERTICAL _v ;
	private SegmentComparator _sc;
	
	public EventPointList(){
		_v= new VERTICAL(0,0);
		_sc = new SegmentComparator(_v);
		C = new TreeSet<Segment>(_sc);
		U = new TreeSet<Segment>(_sc);
		L = new TreeSet<Segment>(_sc);
		events = new LinkedList<EventPoint>() ;
	}
	
	public void addEventPoint(EventPoint p){
		if (events.size() == 0){
			_v.Move(p);
			events.add(p);
		} else {
			if (p.X() == _v.getX()) events.add(p);
			else throw new RuntimeErrorException(null,"Wrong Event Point for EventPointList");
		}
		if (p.isStartingPoint()) U.add(p.getSegment());
		if (p.isEndingPoint())   L.add(p.getSegment());
		else if ( p instanceof IntersectionPoint) {
			IntersectionPoint tp = (IntersectionPoint)p;
			if (!tp.isEndPointSeg())C.add(tp.getSegment());
			if (!tp.isEndPointSeg2())C.add(tp.getSegment2());
		} 
	}
	
	
	
	public TreeSet<Segment> UCL()
	{
		TreeSet<Segment> result = new TreeSet<Segment>(_sc);
		result.addAll(C);
		result.addAll(U);
		result.addAll(L);
		
		return result;
	}
	public TreeSet<Segment> UC()
	{
		TreeSet<Segment> result = new TreeSet<Segment>(_sc);
		result.addAll(C);
		result.addAll(U);
				
		return result;
	}
	
	@Override
	public String toString(){
		
		return U.toString()+C.toString()+L.toString();
	}
	
}
