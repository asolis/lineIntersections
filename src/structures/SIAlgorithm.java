// ==========================================================================
// $Id: SIAlgorithm.java 10 2010-12-15 04:53:00Z a.solis.m $
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
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import events.SweepAlgorithmListener;
import structures.Line.VERTICAL;

public class SIAlgorithm {
	
	private  XMapStructure Q;
	private  VERTICAL sweep_line;
	private  YTreeStructure T;
	
	private EventPoint last;
	private LinkedList<SweepAlgorithmListener> listener;		
	private LinkedList<Entry<EventPoint, EventPointList>> intersections;
	private LinkedList<Segment> _segments;
	
	public SIAlgorithm(){
		listener = new LinkedList<SweepAlgorithmListener>();
		intersections = new LinkedList<Map.Entry<EventPoint,EventPointList>>();
		Q = new XMapStructure();
	}
	public synchronized void addSweepAlgorithmListener( SweepAlgorithmListener l ) {
        listener.add( l );
    }

	public synchronized void removeSweepAlgorithmPointListener( SweepAlgorithmListener l ) {
	    listener.remove( l );
	}
	public synchronized void clearSweepAlgorithmListener()
	{
		listener.clear();
	}
	public void initialize(LinkedList<Segment> segments){
		//Initialize intersection storage
		if (segments.size() < 1) return;
		intersections = new LinkedList<Map.Entry<EventPoint,EventPointList>>();
		
		//Initialise eventQueue
		Q = new XMapStructure();
		
		for (SweepAlgorithmListener l : listener){
			Q.addEventPointListener(l);
		}
		
		for (Segment segment : segments) {
			Q.put(segment.FIRST());
			Q.put(segment.LAST());
		}		
		//Initialise vertical sweep line
		sweep_line = new VERTICAL(0,0);
		last = Q.firstKey();
		
		//Initialise YTreeStructure
		T = new YTreeStructure(sweep_line);
		_segments = segments;
	}
	
	public void FINDINTERSECTIONS_NEXT(){
		if(!Q.isEmpty()){
			Entry<EventPoint, EventPointList> p = Q.pollFirstEntry();  //retrieve next event point
			sweep_line.Move(p.getKey());				               //vertical sweep line move to event X coordinates
			for (SweepAlgorithmListener l : listener){
				l.lineMoved(p);
			}
			HANDLEEVENTPOINT(p);
			last = p.getKey();
		} else {
			for (SweepAlgorithmListener l : listener){
				l.lineEnded();
			}
		}
	}
	
	public void FINDINTERSECTIONS(LinkedList<Segment> segments){
		
		initialize(segments);
		while(!Q.isEmpty()){
			Entry<EventPoint, EventPointList> p = Q.pollFirstEntry();  //retrieve next event point
			sweep_line.Move(p.getKey());				               //vertical sweep line move to event X coordinates
			for (SweepAlgorithmListener l : listener){
				l.lineMoved(p);
			}
			HANDLEEVENTPOINT(p);
			last = p.getKey();
		}
		
		for (SweepAlgorithmListener l : listener){
			l.lineEnded();
		}
			
	
	}
	private void HANDLEEVENTPOINT(Entry<EventPoint, EventPointList> d) {
		EventPoint p    = d.getKey();
		EventPointList l= d.getValue();
		TreeSet<Segment> U = l.U;
		TreeSet<Segment> L = l.L;
		TreeSet<Segment> C = l.C;
		
		if ( (U.size() + L.size() + C.size())> 1){
			intersections.add(d);
			for (SweepAlgorithmListener lis : listener){
				lis.intersectionFound(d);
			}			
		}
		sweep_line.Move(last);
		T.removeAll(L); T.removeAll(C);	
		sweep_line.Move(p);
		T.addAll(U);    T.addAll(C);

		for (SweepAlgorithmListener lis : listener){
			lis.treeChanged(T);
		}
		
		
		if (U.size() + C.size() == 0){
			Segment sl = T.lower(L.first());
			Segment sr = T.higher(L.last());
			FINDNEWEVENT(sl,sr,p);
		} else {
			TreeSet<Segment> UC = l.UC();
			Segment s_l = UC.first();
			Segment sl = T.lower(s_l);
			FINDNEWEVENT(s_l,sl,p);
			
			Segment s_r = UC.last();
			Segment sr  = T.higher(s_r);
			FINDNEWEVENT(s_r,sr,p);			
		}
	}
	private void FINDNEWEVENT(Segment sl, Segment sr, EventPoint p) {
		if (sl == null || sr == null) return;
		for (SweepAlgorithmListener lis : listener){
			lis.intersectionPair(sl, sr);
		}
		if (sl.Intersects(sr)) {
			Point2D.Double i = sl.Intersection(sr);
			if (i.x > p.X() || (i.x == p.X() && i.y > p.Y())) {
				Q.put(new IntersectionPoint(i.x, i.y, sl, sr));
			}
		}
	}
	
}
