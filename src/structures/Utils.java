// ==========================================================================
// $Id: Utils.java 10 2010-12-15 04:53:00Z a.solis.m $
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

public class Utils
{
    public static boolean LeftTurn(Point2D.Double pivot, Point2D.Double a, Point2D.Double b)
    {
        return (TriangleArea(pivot, a, b) > 0);
    }
    public static boolean RightTurn(Point2D.Double pivot, Point2D.Double a, Point2D.Double b)
    {
        return (TriangleArea(pivot, a, b) < 0);
    }
    public static boolean AreAlign(Point2D.Double pivot, Point2D.Double a, Point2D.Double b)
    {
        return (TriangleArea(pivot, a, b) == 0);
    }
    public static double TriangleArea(Point2D.Double pivot, Point2D.Double a, Point2D.Double b)
    {
        double area = 0;
        double D = ((a.x - pivot.x) * (b.y - pivot.y)) -
                  ((a.y - pivot.y) * (b.x - pivot.x));
        area = D / 2.0;

        return area;

    }


}