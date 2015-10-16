// ==========================================================================
// $Id: LineSegment.java 10 2010-12-15 04:53:00Z a.solis.m $
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

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import structures.SIAlgorithm;
import structures.Segment;

public class LineSegment extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser f = new JFileChooser();
    private SIAlgorithm algorithm = new SIAlgorithm();
   
    public LineSegment() {
        initComponents();
    }

  
      private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        eventVisualizer1 = new gui.EventVisualizer();
        jScrollPane1 = new javax.swing.JScrollPane();
        yVisualizer1 = new gui.YVisualizer();
        SegmentVisualizer = new SegmentVisualizer();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Load = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Options = new javax.swing.JMenu();
        EditMode = new javax.swing.JCheckBoxMenuItem();
        Animation = new javax.swing.JCheckBoxMenuItem();
        Clear = new javax.swing.JMenuItem();
        Run = new javax.swing.JMenu();
        Help = new javax.swing.JMenu();
        Start = new javax.swing.JMenuItem();
        Next = new javax.swing.JMenuItem();
        About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Segment Intersections");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        eventVisualizer1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout eventVisualizer1Layout = new javax.swing.GroupLayout(eventVisualizer1);
        eventVisualizer1.setLayout(eventVisualizer1Layout);
        eventVisualizer1Layout.setHorizontalGroup(
            eventVisualizer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        eventVisualizer1Layout.setVerticalGroup(
            eventVisualizer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        yVisualizer1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout yVisualizer1Layout = new javax.swing.GroupLayout(yVisualizer1);
        yVisualizer1.setLayout(yVisualizer1Layout);
        yVisualizer1Layout.setHorizontalGroup(
            yVisualizer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        yVisualizer1Layout.setVerticalGroup(
            yVisualizer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(yVisualizer1);

        SegmentVisualizer.setBackground(new java.awt.Color(255, 255, 255));
        SegmentVisualizer.setAlignmentX(0.0F);
        SegmentVisualizer.setAlignmentY(0.0F);

        javax.swing.GroupLayout SegmentVisualizerLayout = new javax.swing.GroupLayout(SegmentVisualizer);
        SegmentVisualizer.setLayout(SegmentVisualizerLayout);
        SegmentVisualizerLayout.setHorizontalGroup(
            SegmentVisualizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        SegmentVisualizerLayout.setVerticalGroup(
            SegmentVisualizerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventVisualizer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SegmentVisualizer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(SegmentVisualizer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventVisualizer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        File.setText("File");

        Load.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        Load.setText("Load Segments from file...");
        Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadActionPerformed(evt);
            }
        });
        File.add(Load);

        Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Save.setText("Save Segments to file...");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        File.add(Save);

        jMenuBar1.add(File);

        Options.setText("Options");

        EditMode.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        EditMode.setSelected(false);
        EditMode.setText("Edit Mode");
        EditMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditModeActionPerformed(evt);
            }
        });
        Options.add(EditMode);
        Animation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        Animation.setSelected(false);
        Animation.setText("Animation Mode");
        Animation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnimationModeActionPerformed(evt);
            }
        });
        Options.add(Animation);
        Clear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        Options.add(Clear);

        jMenuBar1.add(Options);

        Run.setText("Run");
        Start.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        Start.setText("Start");
        Run.add(Start);
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunActionPerformed(evt);
            }
        });
        Next.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK));
        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        Run.add(Next);

        jMenuBar1.add(Run);
        Help.setText("Help");
        About.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        Help.add(About);
        
        jMenuBar1.add(Help);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    protected void AboutActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(this, "<html>COMP 5008 Computational Geomertry<br/>" +
				"Final Project<br/>" +
				"(C)opyright:<br/>" +
				"<strong>Andres Solis Montero</strong><br/>" +
				"University of Ottawa<br/>" +
				"asoli094@uottawa.ca<br/>");
	}


	protected void AnimationModeActionPerformed(ActionEvent evt) {
		SegmentVisualizer.AMODE = Animation.isSelected();
	}

	protected void NextActionPerformed(ActionEvent evt) {
		algorithm.FINDINTERSECTIONS_NEXT();
	}

	protected void RunActionPerformed(ActionEvent evt) {
		eventVisualizer1.Clear();
		yVisualizer1.Clear();
		SegmentVisualizer.setSegments(SegmentVisualizer.getSegments());
		algorithm.clearSweepAlgorithmListener();
		algorithm.addSweepAlgorithmListener(SegmentVisualizer);
		algorithm.addSweepAlgorithmListener(eventVisualizer1);
		algorithm.addSweepAlgorithmListener(yVisualizer1);
		algorithm.initialize(SegmentVisualizer.getSegments());
	}

	protected void ClearActionPerformed(ActionEvent evt) {
		SegmentVisualizer.Clear();
		eventVisualizer1.Clear();
		yVisualizer1.Clear();
	}

	protected void EditModeActionPerformed(ActionEvent evt) {
	       SegmentVisualizer.EDITMODE = EditMode.isSelected();
		
	}

	private void LoadActionPerformed(java.awt.event.ActionEvent evt) {
            
        int rV = f.showOpenDialog(this);
        if (rV == JFileChooser.APPROVE_OPTION){
        	try{
        		
        		FileReader fin = new FileReader(f.getSelectedFile());
        		BufferedReader din
                = new BufferedReader(fin);
       
        		while(true){
        			String line = din.readLine();
        			if (line == null) break;
        			StringTokenizer st = new StringTokenizer(line);
        		     while (st.countTokens() == 4) {
        		    	 double x1 = Float.parseFloat(st.nextToken());
        		    	 double y1 = Float.parseFloat(st.nextToken());
        		    	 double x2 = Float.parseFloat(st.nextToken());
        		    	 double y2 = Float.parseFloat(st.nextToken());
        		    	 SegmentVisualizer.AddSegment(new Segment(x1,y1,x2,y2));
        		     }      			
        		}
        	
        		
        	}catch (EOFException e){
        		System.err.println("Error: " + e.getMessage());
        	} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        	
        }
    }

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {
        int rV = f.showSaveDialog(this); 
        if (rV == JFileChooser.APPROVE_OPTION){
        	 try{
        		    // Create file 
        		    FileWriter fstream = new FileWriter(f.getSelectedFile());
        		    BufferedWriter out = new BufferedWriter(fstream);
        		    for (Segment s: SegmentVisualizer.getSegments()){
        		    	out.write(String.format("%.2f %.2f %.2f %.2f",
        		    						    s.FIRST().X(),
        		    						    s.FIRST().Y(),
        		    						    s.LAST().X(),
        		    						    s.LAST().Y()));
        		    	out.newLine();
        		    }
        		    //Close the output stream
        		    out.close();
        		    }catch (Exception e){//Catch exception if any
        		      System.err.println("Error: " + e.getMessage());
        		    }
        }
    }

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LineSegment().setVisible(true);
            }
        });
    }

    private javax.swing.JMenuItem Clear;
    private javax.swing.JCheckBoxMenuItem EditMode;
    private javax.swing.JCheckBoxMenuItem Animation;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Load;
    private javax.swing.JMenuItem Start;
    private javax.swing.JMenuItem Next;
    private javax.swing.JMenu Options;
    private javax.swing.JMenu Run;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem About;
    private javax.swing.JMenuItem Save;
    private SegmentVisualizer SegmentVisualizer;
    private gui.EventVisualizer eventVisualizer1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private gui.YVisualizer yVisualizer1;
   

}
