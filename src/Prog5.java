

import java.awt.Color;      // access Java Color Class
import java.util.concurrent.TimeUnit;
/*+----------------------------------------------------------------------
||
||  Class Prog5.java
||
||         Author:  Spencer Klinge
||
||        Purpose:  Graphicly represents the mathematical progression of 
					Cyclic Space carried out by Demon.java. Class acts
					Entirely as Demon.java's main method.
||
||  Inherits From: 	N/A
||
||     Interfaces:  N/A
||
|+-----------------------------------------------------------------------
||
||      Constants:  PIXEL_SIZE- Virtual Pixel size on Graph pane.
					COLS-		Number of numerical values in x axis.
					ROWS-		Number of values in y axis
||
|+-----------------------------------------------------------------------
||
||   Constructors:  N/A
||
||  Class Methods:  [List the names, arguments, and return types of all
||                   public class methods.]
||
||  Inst. Methods:  [List the names, arguments, and return types of all
||                   public instance methods.]
||
++-----------------------------------------------------------------------*/


public class Prog5 {

    final static int PIXEL_SIZE = 3;  // 'virtual' pixel size in real pixels
    final static int COLS = 100;        // x-dimension in Picture class
    final static int ROWS = 100;        // y-dimension "  "       "

    /*=============================================================================
    |   Assignment:  Program #[5]: Demons of Cyclic Space
    |       Author:  [Spencer Klinge (sklinge@email.arizona.edu)]
    | Sect. Leader:  [Lizzie]
    |
    |       Course:  CSC227
    |   Instructor:  L. McCann
    
    |     Due Date:  Tuesday March 4th, 2014, 9:15PM
    |
    |  Description:  The Goal of this program is to graphiclly represent the pogresssion
    				 of Cyclic Space Using a Jpane. Each 'virtual' is represend using a number
    				 of 'real' pixles, and each number is represend by a 1d array "palette" of colors
    |                
    | Deficiencies: N/A
    *===========================================================================*/
    public static void main (String [] args) throws InterruptedException {
    	Demon ono= new Demon(ROWS, COLS,13);//sets demon class to have 100 rows and 100 coloms with 13 states
        Picture  pic;       // drawing window object reference
        Color [] palette =  // array of available color objects
                  { Color.BLACK,  Color.BLUE, Color.RED,    Color.GREEN, Color.darkGray, Color.GRAY,
                    Color.ORANGE, Color.PINK, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.LIGHT_GRAY,  Color.WHITE };

                // Create the drawing window, with enough actual pixels to
                // accommodate the size of our 'virtual' pixels

        pic = new Picture (COLS * PIXEL_SIZE, ROWS * PIXEL_SIZE);
        for(int i= 0; i< 250; i++){//loop for advances

        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
            	int color= ono.getUniverse()[row][col]; 

                        // ... and fill the 'pixel' with it.  Probably better
                        // if this pair of loops were in a separate method,
                        // don't you think?

                
                
                for (int r = row*PIXEL_SIZE; r < (row+1)*PIXEL_SIZE; r++) {		
                    for (int c = col*PIXEL_SIZE; c < (col+1)*PIXEL_SIZE; c++) {
                        pic.set(c,r,palette[color]);  // note that set() wants
                                                      // the column first
                    }
                }

            }
        }
    
        pic.show();   // show the result!
        //TimeUnit.MILLISECONDS.sleep(200); //Paues
        ono.advance();
        }
        //System.out.println("\nStop the program by closing the picture window.");
        	
    }   // main

}  // Pattern