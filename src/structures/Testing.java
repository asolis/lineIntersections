// ==========================================================================
// $Id: Testing.java 10 2010-12-15 04:53:00Z a.solis.m $
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
import java.util.Map.Entry;
import java.util.TreeSet;

import events.SweepAlgorithmListener;

import structures.Line.VERTICAL;

public class Testing  implements SweepAlgorithmListener{
	
	
	public static XMapStructure Q;
	public static VERTICAL sweep_line;
	public static YTreeStructure T;
	
	private static EventPoint last;
	
	public static void main(String[] args){
		Segment s1 = new Segment(1, 1, 10, 10);
		Segment s2 = new Segment(2, 8, 12, 4);
		Segment s3 = new Segment(9, 3, 9, 12);
		Segment s4 = new Segment(10, 10, 20, 6);
		Segment s5 = new Segment(11, 6, 22, 6);
		Segment s6 = new Segment(12, 4, 18, 4);
		Segment s7 = new Segment(2, 13, 9, 13);
		
		Point2D.Double t1 = s3.Intersection(new Segment(9, -2, 9, -1));
		
		
		LinkedList<Segment> segments = new LinkedList<Segment>();
		segments.add(s1);
		segments.add(s2);
		segments.add(s3);
		segments.add(s4);
		segments.add(s5);
		segments.add(s6);
		segments.add(s7);
		
		SIAlgorithm alg  = new SIAlgorithm();
		Testing test = new Testing();
		alg.addSweepAlgorithmListener(test);
		alg.FINDINTERSECTIONS(segments);
		//FINDINTERSECTIONS(segments);
	
	int abc =3;	
	abc = abc/2;
	
		
	}
	
	public static void FINDINTERSECTIONS(LinkedList<Segment> segments){
		//Initialise eventQueue
		Q = new XMapStructure();
		for (Segment segment : segments) {
			Q.put(segment.FIRST());
			Q.put(segment.LAST());
		}		
		//Initialise vertical sweep line
		sweep_line = new VERTICAL(0,0);
		
		//Initialise YTreeStructure
		T = new YTreeStructure(sweep_line);
		last = Q.firstKey();
		while(!Q.isEmpty()){
			Entry<EventPoint, EventPointList> p = Q.pollFirstEntry();  //retrieve next event point
			sweep_line.Move(p.getKey());				               //vertical sweep line move to event X coordinates
			HANDLEEVENTPOINT(p);
			last = p.getKey();
		}
	
	}

	private static void HANDLEEVENTPOINT(Entry<EventPoint, EventPointList> d) {
		// TODO Auto-generated method stub
		EventPoint p    = d.getKey();
		EventPointList l= d.getValue();
		TreeSet<Segment> U = l.U;
		TreeSet<Segment> L = l.L;
		TreeSet<Segment> C = l.C;
		
		if ( (U.size() + L.size() + C.size())> 1){
			System.out.println(String.format("Intersection point: %s", d));
		}
		sweep_line.Move(last);
		T.removeAll(L); T.removeAll(C);	
		sweep_line.Move(p);
		T.addAll(U);    T.addAll(C);
		
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

	private static void FINDNEWEVENT(Segment sl, Segment sr, EventPoint p) {
		if (sl == null || sr == null) return;
		if (sl.Intersects(sr)) {
			Point2D.Double i = sl.Intersection(sr);
			if (i.x > p.X() || (i.x == p.X() && i.y > p.Y())) {
				Q.put(new IntersectionPoint(i.x, i.y, sl, sr));
			}
		}
	}

	@Override
	public void newEventPointDetected(EventPoint e) {
		// TODO Auto-generated method stub
		//System.out.println(String.format("Event Point (%.2f,%.2f) added.", e.X(),e.Y()));
	}

	@Override
	public void lineMoved(Entry<EventPoint, EventPointList>  v) {
		// TODO Auto-generated method stub
		//System.out.println(String.format("Line at X=%.2f", v.getX()));
	}

	@Override
	public void treeChanged(YTreeStructure tree) {
		// TODO Auto-generated method stub
		System.out.print("Y-structure");
		System.out.println(tree);
	}

	@Override
	public void intersectionFound(Entry<EventPoint, EventPointList> d) {
		// TODO Auto-generated method stub
		System.out.print("Intersection:");
		System.out.println(d);
	}

	@Override
	public void intersectionPair(Segment s1, Segment s2) {
		// TODO Auto-generated method stub
		//System.out.print("Intersection:");
		//System.out.println(String.format("[%s,%s]", s1,s2));
	}

	@Override
	public void lineEnded() {
		// TODO Auto-generated method stub
		
	}
}
