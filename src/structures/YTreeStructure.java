// ==========================================================================
// $Id: YTreeStructure.java 10 2010-12-15 04:53:00Z a.solis.m $
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

import java.util.TreeSet;
import structures.Line.VERTICAL;
public class YTreeStructure extends TreeSet<Segment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VERTICAL _vline;
	
	public YTreeStructure(VERTICAL line){
		super(new SegmentComparator(line));
		_vline = line;
	}
	
}
