// ==========================================================================
// $Id: XMapStructure.java 10 2010-12-15 04:53:00Z a.solis.m $
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
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import events.SweepAlgorithmListener;

public class XMapStructure  extends TreeMap<EventPoint,EventPointList>{

	private static final long serialVersionUID = 1L;
	
	private LinkedList<Map.Entry<EventPoint,EventPointList>> history;
	private LinkedList<SweepAlgorithmListener> listener;
	public XMapStructure(){
		super();
		listener =new LinkedList<SweepAlgorithmListener>();
		history = new LinkedList<Map.Entry<EventPoint,EventPointList>>();
	}
	
	public EventPointList put(EventPoint p){
		if (!this.containsKey(p)){
			super.put(p,new EventPointList());
			for (SweepAlgorithmListener l: listener){
				l.newEventPointDetected(p);
			}
		}
		EventPointList tmp = this.get(p);
		tmp.addEventPoint(p);
		return tmp;
	}
	
	@Override
	public Map.Entry<EventPoint, EventPointList> pollFirstEntry(){
		Entry<EventPoint, EventPointList> p = super.pollFirstEntry();
		history.push(p);
		return p;
	}
	

	
	public synchronized void addEventPointListener( SweepAlgorithmListener l ) {
        listener.add( l );
    }

	public synchronized void removeEventPointListener( SweepAlgorithmListener l ) {
	    listener.remove( l );
	}

	
}
