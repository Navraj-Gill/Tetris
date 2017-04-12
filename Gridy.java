import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Gridy{
    
    public static final int WIDTH = 12;
    public static final int HEIGHT = 24;
    public static final int SQUARE_SIZE = 26;
    
    public static final int WINDOW_WIDTH = 319;
    public static final int WINDOW_HEIGHT = 670;

    private static int score=0;
    private static Boolean gameEnd;
    private int x;
    private int y;
    private static Color[][] box;
    private static Color[][] grid;
    private Color[][] miniGrid;
    private Boolean hitBottom;
    private Boolean hitSide=false;
    private Boolean hitPiece=false;
    private Boolean lineIsFull=false;
    private int lineNeedToBeClear;
       
    public Gridy(){
	gameEnd=false;
	Grid();	
    }

    public Color[][] getUpdate(){  	
	return grid;
    }

    public int getScore(){
	return score;
    }
    public Boolean getEndGame(){
	return gameEnd;
    }
    public void Grid() {
	box = new Color[WIDTH][HEIGHT];
	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT - 1; k++) {
		if(i == 0 || i == WIDTH - 1 || k == HEIGHT - 2) {
		    box[i][k] = Color.PINK;
		}		
		else {
		    box[i][k] = Color.WHITE;
		}
	    }
	}
	grid=new Color[WIDTH][HEIGHT];
	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT - 1; k++) {	        
	        grid[i][k]=box[i][k];
	       
	    }
	}
    }
    public void update(int height){
	lineNeedToBeClear=height;
	if(lineIsFull==true){
	    for(int i = 1; i < WIDTH-1; i++) {
		grid[i][lineNeedToBeClear]=Color.WHITE;}
	    for(int k =lineNeedToBeClear; k>=0 ;k--) {
		for(int i = 1; i < WIDTH-1; i++) {		
		    if(grid[i][k]==Color.BLACK){
			grid[i][k]=Color.WHITE;
			grid[i][k+1]=Color.BLACK;
			
			}
		}
	    }
	}
    }
    public void update(int x, int y, Color[][] miniGrid2){
        for(int i = 0; i < 4; i++) {
	    for(int k = 0; k < 4; k++) {
		if(miniGrid2[i][k]!=Color.WHITE){
		    grid[(i+x)][(k+y-1)]=Color.BLACK;
		}
	        
	    }
	}
    }
          	         
    
    public boolean wallCollision(int x, int y,Color[][]miniGrid2) {
        miniGrid=miniGrid2;
	    hitSide=false;
	    for(int i = 0; i < 4; i++) {
		for(int k = 0; k <4 ; k ++) {
		    if(miniGrid[i][k]!=Color.WHITE){
			if(grid[(i+x)][(k+y)]==Color.PINK||grid[(i+x)][(k+y)]==Color.BLACK){
			    hitSide=true;			    
			    return hitSide;
			}
		    }		        

		}
	    }
	    return hitSide;
	        
	}
    
    public boolean bottomCollision(int x1, int y1, Color[][] miniGrid2){
	x=x1;
	y=y1;
        miniGrid=miniGrid2;
	hitBottom=false;
	 for(int i = 0; i < 4; i++) {
	     for(int k = 0; k < 4; k ++) {
		 if(miniGrid[i][k]!=Color.WHITE){
		     if(grid[(i+x)][(k+y)]==Color.PINK||grid[(i+x)][(k+y)]==Color.BLACK){		        
			 hitBottom=true;			 
			 return hitBottom;
		     }
		 }		        
		 
	     }
	 }
	 return hitBottom;
    }
    
    public void endGame(){
	for(int i=1; i< WIDTH-1; i++){
	    if(grid[i][1]==Color.BLACK){
		gameEnd=true;	        
	    }
	}
    }
    public void LineRemover(){
	int k=1;

	for(k = HEIGHT-1; k >=0; k --) {	
	    lineIsFull=true;
	    lineNeedToBeClear=0;
	    for(int i = 1; i < WIDTH-1; i++) {
		if(grid[i][k]!=Color.BLACK){
			lineIsFull=false;
			break;
		     }		 
	     }
	    if(lineIsFull==true){
		score=score+1;
		lineNeedToBeClear=k;
		update(k);
		LineRemover();
	    }
	}    
    }
}