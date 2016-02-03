// ==========================================================================
// $Id: EventVisualizer.java 10 2010-12-15 04:53:00Z a.solis.m $
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
