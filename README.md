### A sweep line algorithm visualizer for listing all crossings in a set of line segments.

For an input consisting of n line segments with k crossings, the algorithm takes time O((n + k) log n). 

### Requirements
Java installed and in your path system variable 

###  Compiling & Running: 
	Windows
	a) run  "compile.bat" (this will create a bin folder)
	b) run  "run.bat"
	Linux / Mac
	a) run ./compile.sh
	b) run ./run.sh
	
		
###2. Creating Jar ('jar.exe' application must be in your Path)
	Windows
	a) run "compile.bat" 
	b) run "create_jar.bat"  (this creates a runnable LineSegments.jar file in the current folder).

	
3. Using the program

	The input of the program can be loaded from a file (each line has 4 points representing a Segment "x1 y1 x2 y2")
	or you can draw the line segments into the application.
	
	Load "Segment files":
		a) Run the application &  Click or (Ctrl+L) "Load Segments from file..." under "File" Menu 
		b) Select a file with a correct format, some examples are provided under the "data" folder.
		
	Draw your Line Segments:
		a) Run the application 
		b) Turn "Edit Mode" on (Shift+E) under "Options" menu. 
		c) draw your line segments using your mouse. (as many you like in any orientation and slope).
		
	Running the algorithm:
		a) After you load or draw line segments.
		b) Click "Start" under "Run" menu  or (Shift+S). 
		c) To go through the algorithms steps click "Next" under "Run" menu or (Shift+N).
		d) If you want to restart the algorithm just "Run">>"Start" again ....
		e) If you want to see some more added animations turn On "Animation" under "Options".
		
	