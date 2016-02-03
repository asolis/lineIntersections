// ==========================================================================
// $Id: YVisualizer.java 10 2010-12-15 04:53:00Z a.solis.m $
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import structures.EventPoint;
import structures.EventPointList;
import structures.Segment;
import structures.YTreeStructure;

import events.SweepAlgorithmListener;

public class YVisualizer extends javax.swing.JPanel implements SweepAlgorithmListener {

	private static final long serialVersionUID = 1L;
	private LinkedList<SegmentColor> segments;
    private LinkedList<String> before;
   
    public YVisualizer() {
        before = new LinkedList<String>();
        segments = new LinkedList<SegmentColor>();
        initComponents();
    }

    public void Clear(){
    	before.clear();
    	segments.clear();
    	repaint();
    }
    public void setList(LinkedList<String> l){
        Collection missing = new LinkedList(before);
        missing.removeAll(l);

        Collection added = new LinkedList(l);
        added.removeAll(before);
        
        segments.clear();
        if (l.size() == before.size() &&
            missing.isEmpty() &&
            added.isEmpty()){
            for (int i = 0; i < l.size(); i++){
                SegmentColor temp = new SegmentColor();
                temp.name = l.get(i);
                if (!before.get(i).equals(l.get(i))){
                    temp.color = Color.blue;
                }else {
                    temp.color = Color.black;
                }
                segments.add(temp);
            }
        }else {
            for (String s: l){
                SegmentColor temp = new SegmentColor();
                temp.name = s;
                if (added.contains(s)){
                   temp.color = Color.red;
                } else {
                    temp.color = Color.black;
                }
                segments.add(temp);
            }
        }
        before = (LinkedList<String>) l.clone();
        repaint();
    }

  
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }


    @Override
    public void paintComponent(Graphics g1){
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                            RenderingHints.VALUE_ANTIALIAS_ON);


        int y = 15;
        for (SegmentColor s: segments){
            g.setColor(s.color);
            g.drawString(s.name, 15, y);
            y+=22;
        }
        this.setPreferredSize(new Dimension(60,y));
    }


	@Override
	public void newEventPointDetected(EventPoint e) {
		
	}


	@Override
	public void lineMoved(Entry<EventPoint, EventPointList> v) {
		
	}


	@Override
	public void treeChanged(YTreeStructure tree) {
		
		LinkedList<String> list = new LinkedList<String>();
		Iterator<Segment> it = tree.descendingIterator();
		while(it.hasNext()){
			list.add(it.next().getName());
		}
		setList(list);
	}


	@Override
	public void intersectionFound(Entry<EventPoint, EventPointList> d) {
				
	}


	@Override
	public void intersectionPair(Segment s1, Segment s2) {
		
	}

	@Override
	public void lineEnded() {
		
	}
   

}
