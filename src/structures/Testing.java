// ==========================================================================
// $Id: Testing.java 10 2010-12-15 04:53:00Z a.solis.m $
// Base class of all operations
/**************************************************************************************************
 **************************************************************************************************
 
     BSD 3-Clause License (https://www.tldrlegal.com/l/bsd3)
     
     Copyright (c) 2010 Andrés Solís Montero <http://www.solism.ca>, All rights reserved.
     
     
     Redistribution and use in source and binary forms, with or without modification,
     are permitted provided that the following conditions are met:
     
     1. Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
     2. Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the documentation
        and/or other materials provided with the distribution.
     3. Neither the name of the copyright holder nor the names of its contributors
        may be used to endorse or promote products derived from this software
        without specific prior written permission.
     
     THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
     AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
     IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
     ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
     LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
     DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
     LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
     THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
     OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
     OF THE POSSIBILITY OF SUCH DAMAGE.
 
 **************************************************************************************************
 **************************************************************************************************/
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
