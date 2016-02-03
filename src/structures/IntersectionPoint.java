// ==========================================================================
// $Id: IntersectionPoint.java 10 2010-12-15 04:53:00Z a.solis.m $
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
