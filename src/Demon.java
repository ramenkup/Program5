
import java.lang.Math.*;
import java.util.concurrent.TimeUnit;


/*+----------------------------------------------------------------------
||
||  Class DEMON
||
||         Author:  Spencer Klinge
||
||        Purpose:  Handles the mathamatical portion of the of the cellular automation
					according to the parameteres established by Dewdney in his 1989
					article.
||
||  Inherits From:  N/A
||
||     Interfaces:  N/A
|+-----------------------------------------------------------------------
||
||      Constants:  N/A
||
|+-----------------------------------------------------------------------
||
||   Constructors:  Demon
||
||  Class Methods:  N.A
||
||  Inst. Methods:  private void popluate()
					public byte[][] getUniverse()
					public void setUniverse(byte[][] one)
					public void advance()
	CHANGELOG:
	
					-CHANGED POPLUATE: COLOR NO LONGER IS +1
					-WrapCheck: cast colors to byte
++-----------------------------------------------------------------------*/





public class Demon {
	
	private byte[][] newGrid; //Grid Representing most updated version of Grid
	private byte[][] oldGrid; //Grid representing previous version of grid
	private int colors;			//the number of colors
	//boolean done=false;
    /*---------------------------------------------------------------------
    |  Method: public Demon(int x, int y, int z)
    |
    |  Purpose: Constructs an object instance of the Demon class.
    |
    |  Pre-condition: N/A
    |
    |  Post-condition: An object of the Demon class will have been
    |				   instanciated
    |  Parameters: int x= designates the x axis size of the new and old grids.
    			   int y= designates the y asix size of the new and old grids.
    			   int z= designates the number of colors between 0 and n-1 for the grids.
    |
    |  Returns: Constructor- does not return.
    *-------------------------------------------------------------------*/

	public Demon(int x, int y, int z){
		colors=z;
		newGrid= new byte[x][y];
		oldGrid= new byte[x][y];
/*	for(int i=0; i<x; i++){
			for(int n=0; n<y; n++){
				oldGrid[i][n]=0;
			}//for2
		}//for1*/
		this.populate();
		//call populate for newGrid;
		
	}
    /*---------------------------------------------------------------------
    |  Method: Populate()
    |
    |  Purpose: Populates the grid with random int values between zero
    			and and colors-1.
    |
    |  Pre-condition: non-static
    |
    |  Post-condition: The Grids will be full of integer values.
    |
    |  Parameters: N/A
    |
    |  Returns: Void.
    *-------------------------------------------------------------------*/

	private void populate(){
		for(int i=0; i<newGrid.length;i++){
			for(int n=0; n<newGrid[0].length;n++){
				newGrid[i][n]= (byte)((int)(colors) * Math.random()); //Correct adjustment... correct cast?
			}
		}
		
	}
    /*---------------------------------------------------------------------
    |  Method: GetUniverse()
    |
    |  Purpose: returns a reference to the newGrid
    |
    |  Pre-condition:  non-static. object must be constructed.
    |
    |  Post-condition: N/A
    |
    |  Parameters: N/A
    |
    |  Returns: reference to newGrid
    *-------------------------------------------------------------------*/

	public byte[][] getUniverse(){
		byte[][]copy= new byte[oldGrid.length][oldGrid[0].length];
		for(int i=0; i< oldGrid.length;i++){
			for(int n=0; n<oldGrid[0].length;n++){
				copy[i][n]=oldGrid[i][n];
		}
	}
		return copy;
	}
	
    /*---------------------------------------------------------------------
    |  Method: Set Universe
    |
    |  Purpose: For the purpose of testing, allows for preset grids
  	|			to be set to the newGrid from main.
  	|
    |  Pre-condition:  Non-static, object must be instanciated.
    |
    |  Post-condition: N/A
    |
    |  Parameters: byte[][] one: the version of the universe set to become
    |			   newGrid.
    |
    |  Returns:  N/A
    *-------------------------------------------------------------------*/

	public void setUniverse(byte[][] one){
		oldGrid= new byte[one.length][one[0].length];
		for(int i=0; i< oldGrid.length;i++){
			for(int n=0; n<oldGrid[0].length;n++){
				oldGrid[i][n]=one[i][n];
		}
	}
		
	}
		
	
	

		
	
    /*---------------------------------------------------------------------
    |  Method Advance
    |
    |  Purpose: Takes a single step in the cellular automation process outlined
    			In the 1989 article. the method iterates thorough the old version
    			of the array. it looks all 4 directions (up, down, left, right) and
    			looks for a value that is one more that itself (According to the article,
    			0 is 1 more than 7 because of warp-around). the current value then takes on
    			the value of the +1 value, and the method moves onto the next chorrdonate in the grid.
    |
    |  Pre-condition: Grid has been populated.
    |
    |  Post-condition: The grid will be one step closer to its Demon phase.
    |
    |  Parameters: N/A
    |
    |  Returns: void.
    *-------------------------------------------------------------------*/

	public void advance(){
		for(int i=0; i<oldGrid.length;i++){
			for(int j=0; j<oldGrid[0].length;j++){
				if(this.wrapCheck(oldGrid[i][j],oldGrid[i][(j+1)%oldGrid[0].length] )){//Right
					newGrid[i][j]= oldGrid[i][(j+1)%oldGrid[0].length];//RETURNS FALSE NEVER GETS HERE.. NEED THIS?
				}//pass by referance
				else
					if(this.wrapCheck(oldGrid[i][j], oldGrid[(i+1) % oldGrid.length][j])){//down
					newGrid[i][j]= oldGrid[(i+1) % oldGrid.length][j];
				}
				else if(this.wrapCheck(oldGrid[i][j], oldGrid[(i-1 + oldGrid.length) % oldGrid.length][j] )){//Up
					newGrid[i][j]= oldGrid[(i-1 + oldGrid.length) % oldGrid.length][j];
				}
				else if(this.wrapCheck(oldGrid[i][j], oldGrid[i][(j-1 + oldGrid[0].length) % oldGrid[0].length])){//left
					newGrid[i][j]= oldGrid[i][(j-1 + oldGrid[0].length) % oldGrid[0].length];
				}
				//System.out.println(""+ (i-1 + oldGrid.length) % oldGrid.length);
				//System.out.println("Up Element:"+oldGrid[(i-1 + oldGrid.length) % oldGrid.length][j]);
				//System.out.println("J="+j);
			}
		}
	
		//PRINT FOR LOOP
	for(int r=0; r<oldGrid.length;r++){
		for(int c=0; c<oldGrid[0].length;c++){
			oldGrid[r][c]=newGrid[r][c];
		}
	}
	/*for(int i=0; i< newGrid.length;i++){
		for(int n=0; n<newGrid[0].length;n++){
			//System.out.print(newGrid[i][n] + " ");
		}//PRINT INNER FORLOOP
			//System.out.println();
	}*/
	}
			
	private boolean wrapCheck(byte curr, byte neighbor){ //n is current position
		curr= (byte) ((curr+1) % (byte) colors);
		return curr == neighbor;
	
	}
	
	
	/*=============================================================================
	 |   Assignment:  Program #5:  Demons of Cyclic Space
	 |       Author:  Spencer Klinge(sklinge@email.arizona.edu)
	 | Sect. Leader:  Lizzie
	 |
	 |       Course:  CSC227
	 |   Instructor:  L. McCann
	 |     Due Date:  Tuesday March 4th, 2014, 9:15PM
	 |
	 |  Description:  This class displays the effects of cellular automation with behavior
	 				  Described by the 1989 by A.K Dewdney. Cells of different numerical values
	 				  between 0 and n-1 are radomly placed in a grid and are represented as a color.
	 				  To the tick of an imaginary clock, the process from the debris phase all the 
	 				  way to the final demon phase.
	 				  
	 |                
	 | Deficiencies: N/A
	 *===========================================================================*/
	
/*	public static void main(String args[]){
		Demon d= new Demon(3,3,8);
		byte[][] test=new byte[][]{{0,1,2},{7,4,3},{7,5,6}};
		d.setUniverse(test);
		for(int y=0; y< test.length;y++){
			for(int n=0; n<test[0].length;n++){
				System.out.print(test[y][n] + " ");
			}//2nd For Printer
				System.out.println();
		}
		System.out.println();
		for(int a=0; a<4; a++){
			d.advance();
			System.out.println();
		}
		
	}
	*/
	}

	//class
