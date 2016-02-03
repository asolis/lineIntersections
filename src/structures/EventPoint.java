// ==========================================================================
// $Id: EventPoint.java 10 2010-12-15 04:53:00Z a.solis.m $
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
