import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Scanner;
import java.io.*;

public class Startup extends JFrame implements ActionListener, KeyListener{
    public static final int WIDTH = 12;
    public static final int HEIGHT = 24;
    public static final int SQUARE_SIZE = 26;

    public static final int WINDOW_WIDTH = 311;
    public static final int WINDOW_HEIGHT = 670;

    private int rank=0;
    private Boolean scoreSettle=false;
    private String highestScore;
    private String secondplace;
    private String secondScore;
    private Boolean InputScore=false;
    private ArrayList<String> HighScore=new ArrayList<String>();

    private int x=2;
    private int y=2;
    private int score;
    private int dropSpeed=380;

    private Color[][] miniGrid;
    private Color[][] grid;
    private Boolean endGame=false;
    Timer timer;
    Gridy gr=new Gridy();
    TetrisPiece tp=new TetrisPiece();
    //TitlePage t = new TitlePage();



    public Startup(){
	addKeyListener(this);
	miniGrid=tp.getMiniGrid();
	x=tp.getX();
	y=tp.getY();
	grid=gr.getUpdate();
	repaint();
	 ActionListener listener = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
		  endGame=gr.getEndGame();
		  if(endGame==true){
		      timer.stop();
		  }
		  score=gr.getScore();
		  tp.dropping();
		  miniGrid=tp.getMiniGrid();
		  x=tp.getX();
		  y=tp.getY();
		  grid=gr.getUpdate();
		  repaint();

	      }
	     };

	 timer=new Timer(dropSpeed, listener);
	 timer.start();
   /*JButton main = new JButton("Main Menu");
   main.addActionListener(new ActionListener(){
 		public void actionPerformed(ActionEvent ae){
 		  t.GUI();
      dispose();
 		}
  });*/
   //add(main, BorderLayout.SOUTH);
	 setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setLocationRelativeTo(null);
	 setVisible(true);

    }
    public void actionPerformed(ActionEvent n){



    }


    public void keyPressed(KeyEvent l){
      int k = l.getKeyCode();
      if(k == KeyEvent.VK_DOWN){
	  String move="down";
	  tp.setMovement(move);
      }
      if(k == KeyEvent.VK_LEFT){
        String move="left";
	tp.setMovement(move);
      }
      if(k == KeyEvent.VK_RIGHT){
        String move="right";
	tp.setMovement(move);
      }
      if(k == KeyEvent.VK_UP){
        String move="up";
	tp.setMovement(move);
      }
    }

    public void keyTyped(KeyEvent l){}
    public void keyReleased(KeyEvent l){}

    //@Override{
	public void paint(Graphics g) {
	    super.paint(g);

	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT; k++) {

		g.setColor(grid[i][k]);
		g.fillRect(i * SQUARE_SIZE, k * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
	    }

	}

	for(int i = 0; i < 4; i++) {
	    for(int k = 0; k < 4; k++) {
		if(miniGrid[i][k]!=Color.WHITE){
		    g.setColor(miniGrid[i][k]);
		    g.fillRect((i+x)* SQUARE_SIZE, (k+y) * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
		}
	    }
	}

	Font currentFont = g.getFont();
	Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F);
	g.setFont(newFont);
	g.setColor(Color.BLACK);
	g.drawString(""+(score*100),250,50);

	if(endGame==true){
	    currentFont = g.getFont();
	    newFont = currentFont.deriveFont(currentFont.getSize() *2.0F);
	    g.setFont(newFont);
	    g.setColor(Color.RED);
	    g.drawString("GAME OVER",26,250);
	    currentFont = g.getFont();
	    newFont = currentFont.deriveFont(currentFont.getSize() *0.8F);
	    g.setFont(newFont);
	    g.setColor(Color.RED);
	    g.drawString("Score: "+(score*100),70,300);
	    if(InputScore==false){
		FileE();}
	}

	if(InputScore==true){
	    currentFont = g.getFont();
	    newFont = currentFont.deriveFont(currentFont.getSize() *0.7F);
	    g.setFont(newFont);
	    g.setColor(Color.RED);
	    g.drawString(HighScore.get(0),70,350);
	    g.drawString(HighScore.get(1),70,390);
	    g.drawString(HighScore.get(2),70,430);
	}
    }
    //this method is for undate the highest score.

    public void FileE(){
	//ArrayList<String> HighScore=new ArrayList<String>();

	//get user pomps their first name, remove exral character after space.
	int inputScore=score*100;
	String name=JOptionPane.showInputDialog("Enter Your First Name: ");
	String[] name1=name.split(" ");
        String firstName=null;

	if(name1.length>0){
	    firstName=name1[0];
	}
	else{
	    firstName=name;
	}


	String fileName="highScore.txt";

	Scanner input=null;
	try{
	    input=new Scanner(new File(fileName));
	}

	catch(FileNotFoundException fnfe){
		return;
	    }

	catch(NullPointerException npe){
		return;
	    }

	//this while loop will check if there is anything inside of the high Score text file.
	//compares the user's score with the scores that already exist inside the high Score file.
	//if the user's score is higher, then a new top three score will be recorded in the format of rank(1,2 or3) firstName score.
	while(input.hasNextLine()){
	    rank=rank+1;
	    int rankScore=0;
	    String line=input.nextLine();
	    String[] Scores=line.split(" ");
	    String recordScore=Scores[2];
	    int highScore=Integer.parseInt(recordScore);
	    if(scoreSettle==false){
		if(inputScore > highScore){
		    highestScore=rank+" "+firstName+" "+inputScore;
		    scoreSettle=true;
		    secondplace=Scores[1];
		    secondScore=Scores[2];
		}
		else{
		    highestScore=rank+" "+Scores[1]+" "+Scores[2];
		}
	    }
	    else{
		highestScore=rank+" "+secondplace+" "+secondScore;
	    }
	    secondplace=Scores[1];
	    secondScore=Scores[2];
	     HighScore.add(highestScore);
	     rankScore=rankScore+1;
        }

	PrintWriter writer = null;

	try{
	    writer=new PrintWriter(fileName);
	}
	catch(FileNotFoundException fnfe){
	    fnfe.printStackTrace();
	    return;
	}

	//the new top three score will be update here.
	finally{
	    for(int scoreRank=0; scoreRank<3; scoreRank++){
		writer.print(HighScore.get(scoreRank));
		writer.println();}
	    input.close();
	    writer.close();
	    InputScore=true;
	    repaint();
	}
    }

    public static void main(String[] args){

	EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Startup();
            }
	    });
        }

}
