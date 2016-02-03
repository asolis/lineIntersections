// ==========================================================================
// $Id: EventPointList.java 10 2010-12-15 04:53:00Z a.solis.m $
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
