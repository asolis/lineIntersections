// ==========================================================================
// $Id: SweepAlgorithmListener.java 10 2010-12-15 04:53:00Z a.solis.m $
// Interface for Sweep Algorithm events
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

package events;

import java.util.Map.Entry;

import structures.EventPoint;
import structures.EventPointList;
import structures.Segment;
import structures.YTreeStructure;


public interface SweepAlgorithmListener {
	void newEventPointDetected(EventPoint e);
	
	  void lineMoved(Entry<EventPoint, EventPointList>  v);
	   void lineEnded();
	   void treeChanged(YTreeStructure tree);
	void intersectionFound(Entry<EventPoint, EventPointList> d);
	void intersectionPair(Segment s1, Segment s2);
}
