import java.util.Random;
import java.awt.Color;
public class TetrisPiece extends Gridy{
    private String shape;
    private String direction;
    private int rotation=0;
    private int x=5;
    private int y=0;
    private Boolean gameEnd=false;
    private Boolean hitSide=false;
    private Boolean hitBottom=false;
    private Color[][] miniGrid;
    
    public TetrisPiece(){
	shape=random();
	PieceShow();
    }
    private String random(){
	Random rand=new Random();
	 int num=rand.nextInt(7);
	 if(num==0){
	     shape="O";}
	 else if(num==1){
	     shape="L";}
	 else if(num==2){
	     shape="S";}
	 else if(num==3){
	     shape="Z";}
	 else if(num==4){
	     shape="I";}
	 else if(num==5){
	     shape="J";}
	 else if(num==6){
	     shape="T";}
	 return shape;
    }
    
    public int getX(){	
	return x;
    }

    public int getY(){
	return y;
    }

    public Color[][] getMiniGrid(){
	return miniGrid;
    }
    
    public void setMovement(String direction1){
        direction=direction1;
	PieceMovement();
    }
     public Boolean getHitBottom(){
	return hitBottom;
    }
    
    //store 4 sets of cordinates of 7 shapes in an array list called location.
    private void PieceShow(){	
	switch(shape){
	case "O":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==2)||(c==2 && r==1)||(c==2 && r==2)){
			miniGrid[c][r]=Color.CYAN;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	    
	case "L":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==2 && r==0)){
			miniGrid[c][r]=Color.RED;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    
	    break;
	case "S":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==0 && r==2)||(c==1 && r==0)){
			miniGrid[c][r]=Color.GREEN;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case "Z":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==0)||(c==0 && r==1)||(c==1 && r==2)){
			miniGrid[c][r]=Color.ORANGE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }	    
	    break;
	case "J":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==0 && r==2)){
			miniGrid[c][r]=Color.GRAY;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    
	    break;
	case "I":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==3 && r==1)){
			miniGrid[c][r]=Color.YELLOW;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }	    
	    break;
	case "T":
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==0 && r==1)||(c==2 && r==1)){
			miniGrid[c][r]=Color.BLUE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}      
    }
    private void PieceMovement(){	
	switch (direction){	    
	    //moves left
	case "left":
	    x=x-1;
	    hitSide=wallCollision(x,y,miniGrid);
	    if(hitSide==true){
	        x=x+1;
	    }
	    break;	    
	case "down":	    
	    dropping();
	    break;
	    
	    //moves right
	case "right":
	    x=x+1;
	    hitSide=wallCollision(x,y,miniGrid);
	    if(hitSide==true){
	        x=x-1;
	    }
	    break;
	    
        case "up":
	    if (rotation > 4){
		rotation=1;}
	    else{
		rotation=rotation+1;
	    }
	    GetShape();
	    hitSide=wallCollision(x,y,miniGrid);
	    
	    if(hitSide==true){
		
		if(rotation==1){
		    rotation=4;}
		else{
		    rotation=rotation-1;}
		 GetShape();
	    }
	    hitSide=false;
	    
	}
    }

    public void dropping(){	
	    
	    y=y+1;
	    hitBottom=bottomCollision(x,y,miniGrid);
	    gameEnd=getEndGame();
	    if(hitBottom==true){      	     	        			
		update(x,y,miniGrid);
		LineRemover();
		endGame();
		shape=random();
		PieceShow();
		rotation=0;
		y=0;
		x=5;	        
	    }
	    if(gameEnd==true){
		for (int c=0; c<4; c++){
		    for (int r=0; r<4; r++){
			miniGrid[c][r]=Color.WHITE;
		    
		    }
		}
	    }	    
    }

    //Rotation   
   private void GetShape(){
	if (shape.equals("O")){
	}	
	else if (shape.equals("L")){
	    Lshape();
	}
	else if (shape.equals("S")){
	    Sshape();
	}
	else if (shape.equals("Z")){
	    Zshape();
	}
	else if (shape.equals("I")){
	    Ishape();
	}
	else if (shape.equals("J")){
	    Jshape();
	}
	else if (shape.equals("T")){
	    Tshape();
	}
    }

    private void Lshape(){
	switch(rotation){
	case 1:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==2 && r==2)){
			miniGrid[c][r]=Color.RED;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==2 && r==1)||(c==0 && r==1)||(c==0 && r==2)){
			miniGrid[c][r]=Color.RED;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 3:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==2)||(c==1 && r==0)||(c==0 && r==0)){
			miniGrid[c][r]=Color.RED;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    
	    break;
	case 4:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==2 && r==0)){
			miniGrid[c][r]=Color.RED;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}      
    }
     private void Sshape(){
	switch(rotation){
	case 1: case 3:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==0)||(c==1 && r==0)||(c==2 && r==1)){
			miniGrid[c][r]=Color.GREEN;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2: case 4:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==0 && r==2)||(c==1 && r==0)){
			miniGrid[c][r]=Color.GREEN;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}       
    }

    private void Zshape(){
	switch(rotation){
	case 1: case 3:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==2 && r==0)||(c==1 && r==0)||(c==0 && r==1)){
			miniGrid[c][r]=Color.ORANGE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2: case 4:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==0)||(c==0 && r==1)||(c==1 && r==2)){
			miniGrid[c][r]=Color.ORANGE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}
    }

     private void Ishape(){
	switch(rotation){
	case 1: case 3:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==1 && r==3)){
			miniGrid[c][r]=Color.YELLOW;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2: case 4:
	     miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==3 && r==1)){
			miniGrid[c][r]=Color.YELLOW;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}      
    }

     private void Jshape(){
	switch(rotation){
	case 1:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==2 && r==1)||(c==0 && r==1)||(c==0 && r==0)){
			miniGrid[c][r]=Color.GRAY;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==2 && r==0)){
			miniGrid[c][r]=Color.GRAY;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 3:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==2 && r==2)){
			miniGrid[c][r]=Color.GRAY;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 4:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==0 && r==2)){
			miniGrid[c][r]=Color.GRAY;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}
        
    }

     private void Tshape(){
	switch(rotation){
	case 1:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==2 && r==1)||(c==1 && r==0)||(c==1 && r==2)){
			miniGrid[c][r]=Color.BLUE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 2:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==2)||(c==0 && r==1)||(c==2 && r==1)){
			miniGrid[c][r]=Color.BLUE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 3:
	    miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==0 && r==1)||(c==1 && r==0)||(c==1 && r==2)){
			miniGrid[c][r]=Color.BLUE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	case 4:
	   miniGrid=new Color[4][4];
	    for (int c=0; c<4; c++){
		for (int r=0; r<4; r++){
		    if((r==1 && c==1)||(c==1 && r==0)||(c==0 && r==1)||(c==2 && r==1)){
			miniGrid[c][r]=Color.BLUE;
		    }
		    else{
			miniGrid[c][r]=Color.WHITE;
		    }
		}
	    }
	    break;
	}        
    }
}