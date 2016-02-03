// ==========================================================================
// $Id: Segment.java 10 2010-12-15 04:53:00Z a.solis.m $
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

public class Segment {

	
	private EndPoint _first;
	private EndPoint _last;
	private String 	 _uid; 
	private String   _name;
	
	private static int count = 0;
	
	
	public Segment(double x1,double y1, double x2, double y2){
		// Top left corner has 0,0 coordinates
		if ( x1 <= x2 || (x1==x2 && y1 <= y2)){
			_first = new EndPoint(x1, y1, this);
			_last  = new EndPoint(x2, y2, this);		
		} else{
			_last = new EndPoint(x1, y1, this);
			_first  = new EndPoint(x2, y2, this);
		}
		_uid = String.format("%.2f,%.2f,%.2f,%.2f",
							 _first.X(),
							 _first.Y(),
							 _last.X(),
							 _last.Y());
		_name = String.format("s%d", ++count);
	}
	
	public EndPoint FIRST()
	{
		return _first;
	}
	public EndPoint LAST()
	{
		return _last;
	}
	@Override
	public String toString()
	{
		return String.format("%s(%s)", _name,_uid);
	}

	public boolean isVertical(){
		return _first.X() == _last.X();
	}

	@Override 
	public boolean equals(Object arg0){
		return _uid.equals(((Segment)arg0)._uid);
		
	}
		
	private double num(Segment s){
		return (_first.Y()-s._first.Y())*(s._last.X()-s._first.X())-
			   (_first.X()-s._first.X())*(s._last.Y()-s._first.Y());
	}
	private double den(Segment s){
		return (_last.X()-_first.X())*(s._last.Y()-s._first.Y())-
		       (_last.Y()-_first.Y())*(s._last.X()-s._first.X());
	}
	private double num2(Segment s){
		return (_first.Y()-s._first.Y())*(_last.X()-_first.X())-
		  	   (_first.X()-s._first.X())*(_last.Y()-_first.Y());
	}
	public boolean Intersects(Segment s){
		
		double num = num(s);
		double den = den(s);
		if (den == 0 && num == 0 ) 
			return s._first._p.equals(_first._p) || s._last._p.equals(_first._p);
			//return true; //Coincident, check if they touch in one of the endpoints
		if (den == 0) 
			return false; //Parallel
		double num2 = num2(s);
		double r = num/den;
		double S = num2/den;
		
		return (0<=r)&&(r<=1)&&(0<=S)&&(S<=1);
	}
	
	public Point2D.Double Intersection(Segment s){
		
		double num = num(s);
		double den = den(s);
		if (den == 0 && num == 0 ) {
			if (s.FIRST().equals(this.FIRST()))
				return new Point2D.Double(_first.X(),_first.Y()); //Coincident
			if (s.LAST().equals(this.FIRST()))
				return new Point2D.Double(_last.X(),_last.Y());
			
			if (_first._p.distance(s._last._p) <= _last._p.distance(s._first._p))
				return new Point2D.Double(_first.X(),_first.Y());    // if both are parallel and coincident and don't touch
			else return new Point2D.Double(_last.X(),_last.Y()); 
		}
		if (den == 0) 
			return null; //Parallel
		double r = num/den;
		double x = _first.X() + r*(_last.X()-_first.X());
		double y = _first.Y() + r*(_last.Y()-_first.Y());
		
		return new Point2D.Double(x,y);
		
	}
	
	public boolean containsEndPoint(EventPoint p){
		return _first.equals(p) || _last.equals(p);
	}
	
	public String getName(){
		return _name;
	}
	public boolean contains(double x, double y)
	{
		if (( _first._p.y <= y && _last._p.y >= y) && ( _first._p.x <= x && _last._p.x >= x))
			return Utils.AreAlign(_first._p, _last._p, new Point2D.Double(x,y));
		return false;
	}
}
