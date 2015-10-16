// ==========================================================================
// $Id: EventVisualizer.java 10 2010-12-15 04:53:00Z a.solis.m $
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
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.LinkedList;
import java.util.Map.Entry;

import structures.EventPoint;
import structures.EventPointList;
import structures.Segment;
import structures.YTreeStructure;

import events.SweepAlgorithmListener;

public class EventVisualizer extends javax.swing.JPanel implements SweepAlgorithmListener {

	private static final long serialVersionUID = 1L;
	private LinkedList<Point2D.Double> points;
    private double vertical = 0;
    private double horizontal = 15;
   
    public EventVisualizer() {
        points = new LinkedList<Point2D.Double>();
        initComponents();
    }
    public void Clear(){
    	points.clear();
    	vertical=0;
    	repaint();
    }
    public void setVertical(double v){
        vertical = v;
        this.setToolTipText("<html>Event: (13,15)<br/> U: [s1,s2,s3] <br/>C: [s3,35,s5] <br/> L: [] </html>");
    }

    public void setPoints(LinkedList<Point2D.Double> p){
        points = (LinkedList<Double>) p.clone();
    }
   
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
    }

    @Override
    public void paintComponent(Graphics g1){
         super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawLine(0, (int)horizontal, getWidth(), (int)horizontal);

        
        g.setColor(Color.red);
        g.drawLine((int)vertical, 0, (int)vertical, getHeight()-5);

        int diameter = 6;
        for (Point2D.Double p : points){
            g.fillOval(((int)p.getX())-diameter/2, 15-diameter/2, diameter, diameter);
        }
    }

	@Override
	public void newEventPointDetected(EventPoint e) {
		
		 points.add(new Point2D.Double(e.X(),e.Y()));
		 repaint();
	}

	@Override
	public void lineMoved(Entry<EventPoint, EventPointList>  v) {
		
		vertical = v.getKey().X();
		setToolTipText(String.format("<html>Event Point:<br/>" +
									 "%s<br/>" +
									 "U:%s<br/>" +
									 "C:%s<br/>" +
									 "L:%s </html>", 
				       v.getKey(),v.getValue().U,v.getValue().C,v.getValue().L));
		repaint();
	}

	@Override
	public void treeChanged(YTreeStructure tree) {
		
	}

	@Override
	public void intersectionFound(Entry<EventPoint, EventPointList> d) {
		
	}

	@Override
	public void intersectionPair(Segment s1, Segment s2) {
		
	}
	@Override
	public void lineEnded() {
		
		vertical = getWidth();
		setToolTipText("");
		repaint();
	}

   

}
