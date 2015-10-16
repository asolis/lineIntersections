// ==========================================================================
// $Id: DisplayComponent.java 10 2010-12-15 04:53:00Z a.solis.m $
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
package util.Graphics;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Label;

public class DisplayComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	private Label EventPointControl = null;

	/**
	 * This is the default constructor
	 */
	public DisplayComponent() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		EventPointControl = new Label();
		EventPointControl.setText("This is Event Point Control");
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(EventPointControl, null);
	}

}
