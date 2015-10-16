// ==========================================================================
// $Id: SegmentVisualizer.java 10 2010-12-15 04:53:00Z a.solis.m $
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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import events.SweepAlgorithmListener;
import structures.EventPoint;
import structures.EventPointList;
import structures.Segment;
import structures.YTreeStructure;
import util.Graphics.Animation;
import util.Graphics.CountBlinkingShape;
import util.Graphics.Drawable;
import util.Graphics.DrawableShape;
import util.Graphics.TemporalDrawableShape;

 public class SegmentVisualizer extends JPanel implements java.awt.event.MouseListener,
 													   java.awt.event.MouseMotionListener,
 													   SweepAlgorithmListener{

	
	private static final long serialVersionUID = 1L;
	public Point2D.Double _first;
	public boolean        _press = false;
	public boolean        EDITMODE = false;
	public boolean 		  AMODE = false;
	private Color  BC;
	private Stroke BS;
	private BasicStroke dashedStroke = new BasicStroke(1.0f,
			  					  					   BasicStroke.CAP_BUTT,
			  					  					   BasicStroke.JOIN_MITER,
			  					  					   5.0f,new float[]{ 4.0f }, 0.0f);
	
	public double scale;
	private LinkedList<Segment> segments = new LinkedList<Segment>();	
	private Vector<Drawable> elements = new Vector<Drawable>();
	private LinkedList<Animation> animations = new LinkedList<Animation>();
	private double  vertical_line =0;

	
	public void AddShape(Drawable shape){
		elements.add(shape);
		repaint();
	}
	public void AddAnimation(Animation a){
		animations.add(a);
	}
	public void AddSegment(Segment s){
		Line2D.Double l = new Line2D.Double(s.FIRST().X(),s.FIRST().Y(),
							                s.LAST().X(),s.LAST().Y());
		segments.add(s);
		AddShape(new DrawableShape(this,l));
	}
	
	
	public void setSegments(LinkedList<Segment> segments){
		elements.clear();
		animations.clear();
		for (Segment s: segments){
			Line2D.Double l = new Line2D.Double(s.FIRST().X(),s.FIRST().Y(),
												s.LAST().X(),s.LAST().Y());
			
			elements.add(new DrawableShape(this,l));
		}
		this.segments = segments;
		vertical_line = 0;
		repaint();
	}
	public LinkedList<Segment> getSegments(){
		return segments;
	}
	public SegmentVisualizer(){
		this(1.);
	}
	// Construct
	public SegmentVisualizer(double scale) {
	    this.setScale(scale);
	    this.addMouseListener(this);
	    this.addMouseMotionListener(this);
	   
	}

	// Methods
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    shapes(((Graphics2D)g));
	}
	
	// Painting Shapes on top of the background image
	public void shapes(Graphics2D g){
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				   		   RenderingHints.VALUE_ANTIALIAS_ON);
		BC  = g.getColor();
		BS = g.getStroke();
		g.setStroke(dashedStroke);
		g.setColor (Color.red);
		g.draw( new Line2D.Double(vertical_line,0,vertical_line,getHeight()));
		g.setStroke(BS);
		g.setColor(BC);
		if (_press){
			  BC  = g.getColor();
			  BS = g.getStroke();
			  g.setStroke(dashedStroke);
			  g.setColor (Color.gray);
			  Point _second = new Point(MouseInfo.getPointerInfo().getLocation().x,
										MouseInfo.getPointerInfo().getLocation().y);
			  SwingUtilities.convertPointFromScreen(_second, this);
			  g.draw(new java.awt.geom.Line2D.Double(_first,_second));
			  g.setStroke(BS);
			  g.setColor(BC);
		} 
    	Enumeration<Drawable> e = elements.elements(); 
        Drawable obj;
        
       
        while (e.hasMoreElements()) { // step through all vector elements
           obj = (Drawable) e.nextElement();
           obj.setScale(scale);
           obj.draw(g);
           if ( obj instanceof TemporalDrawableShape){
        	   if (((TemporalDrawableShape) obj).Remove()) elements.remove(obj);            
        	   
           }

        } 
        if (animations.size() >0){
        	if (animations.get(0).Remove()) animations.poll();
	        animations.get(0).startAnimation();
	        animations.get(0).draw(g);
	        
        }
        BC  = g.getColor();
		BS = g.getStroke();
		g.setColor(Color.black);
        for (Segment s: segments){
        	g.drawString(s.getName(),(int) s.FIRST().X()- (s.getName().length()*8),(int) s.FIRST().Y());
        }
		g.setStroke(BS);
		g.setColor(BC);
    }

	
	
	public void setScale(double scale) {
	    this.scale = scale;
	    setPreferredSize(new Dimension((int) (getWidth() * scale), (int) (getHeight() * scale)));
	    revalidate();
	    repaint();
	}

	public double getScale() {
	    return scale;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (EDITMODE){
			_press = true;
			_first = new Point2D.Double((double)e.getX(),(double)e.getY());
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (EDITMODE){
			Point2D.Double _second = new Point2D.Double(e.getX(),e.getY());
			if (_first.distanceSq(_second)>9){
				Segment s = new Segment(_first.x,_first.y,e.getX(),e.getY());
				this.AddSegment(s);
			}
			_press = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	
		if (EDITMODE){
		Point _second = new Point(MouseInfo.getPointerInfo().getLocation().x,
				MouseInfo.getPointerInfo().getLocation().y);
		SwingUtilities.convertPointFromScreen(_second, this);
		this.repaint(Math.min((int)_first.x, _second.x)-100,
					 Math.min((int)_first.y, _second.y)-100,
					 Math.abs((int)_first.x- _second.x)+200,
					 Math.abs((int)_first.y- _second.y)+200);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void newEventPointDetected(EventPoint e) {
		double diameter = 6;
		DrawableShape bS = new DrawableShape(this, new java.awt.geom.Ellipse2D.Double(e.X()-diameter/2,e.Y()-diameter/2,diameter,diameter));
		this.AddShape(bS);
	
	}

	@Override
	public void lineMoved(Entry<EventPoint, EventPointList>  v) {

		vertical_line = v.getKey().X();
		double diameter = 8;
		EventPoint k = v.getKey();
		CountBlinkingShape p = new CountBlinkingShape(this, new java.awt.geom.Ellipse2D.Double(
															k.X()-diameter/2,
															k.Y()-diameter/2,
															diameter,
															diameter));
		p.fillable = true;
		p.setColor(Color.red);
		this.AddShape(p);
		repaint();
	}

	@Override
	public void treeChanged(YTreeStructure tree) {
	
	}

	@Override
	public void intersectionFound(Entry<EventPoint, EventPointList> d) {
		
		if (AMODE){
			LinkedList<DrawableShape> list = new LinkedList<DrawableShape>();
			BasicStroke bs = new BasicStroke(2.f);
			for (Segment s: d.getValue().UCL()){
				DrawableShape l = new DrawableShape(this, new java.awt.geom.Line2D.Double(
						s.FIRST().X(),
						s.FIRST().Y(),
						s.LAST().X(),
						s.LAST().Y()));
				l.setColor(Color.BLUE);
				l.setStroke(bs);
				list.add(l);
			
			double diameter = 8;
			EventPoint k = d.getKey();
			
			DrawableShape p = new DrawableShape(this, new java.awt.geom.Ellipse2D.Double(
																k.X()-diameter/2,
																k.Y()-diameter/2,
																diameter,
																diameter));
			p.fillable = true;
			p.setColor(Color.red);
			list.add(p);
			
				
		}
			Animation an = new Animation(this,list);
			
		this.AddAnimation(an);
		
		}
	}

	@Override
	public void intersectionPair(Segment s1, Segment s2) {
		
		if (AMODE){
			BasicStroke bs = new BasicStroke(2.f);
			DrawableShape l = new DrawableShape(this, new java.awt.geom.Line2D.Double(
					s1.FIRST().X(),
					s1.FIRST().Y(),
					s1.LAST().X(),
					s1.LAST().Y()));
			l.setColor(Color.red);
			l.setStroke(bs);
			DrawableShape l2 = new DrawableShape(this, new java.awt.geom.Line2D.Double(
					s2.FIRST().X(),
					s2.FIRST().Y(),
					s2.LAST().X(),
					s2.LAST().Y()));
			l2.setColor(Color.green);
			l2.setStroke(bs);
			LinkedList<DrawableShape> list = new LinkedList<DrawableShape>();
			list.add(l);
			list.add(l2);
			Animation an = new Animation(this, list);
			this.AddAnimation(an);
		
		}
	}
	public void Clear() {
		segments.clear();
		elements.clear();
		animations.clear();
		vertical_line = 0;
		repaint();
	}
	@Override
	public void lineEnded() {
		vertical_line = getWidth();
		repaint();
	}

	
	
		
	
	
    }
