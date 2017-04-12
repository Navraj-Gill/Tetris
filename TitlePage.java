import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class TitlePage extends JFrame{
    public static void Scores(){
	String filename = "highScore.txt";
	try(BufferedReader br = new BufferedReader(new FileReader(filename))){
	    String line;
	    while ((line = br.readLine()) != null){
		JLabel score = new JLabel(line);
	    }
	}catch (IOException e){
	    e.printStackTrace();
	}
    }
    public static void GUI(){
	JFrame frame = new JFrame("Tetris");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JLabel text = new JLabel("TETRIS", SwingConstants.CENTER);
	text.setFont(text.getFont().deriveFont(64.0f));

	JButton start = new JButton("Start");
	JButton score = new JButton("HighScore");

	start.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent ae){
		  new Startup();
		  frame.dispose();
		}
	    });
	score.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent ae){
			Scores();
		}
	    });

	panel.setLayout(new BorderLayout());
	panel.add(text);
	panel2.setLayout(new BorderLayout());
	panel2.add(start, BorderLayout.EAST);
	panel2.add(score, BorderLayout.WEST);

	frame.add(panel);
	frame.add(panel2, BorderLayout.SOUTH);
	frame.setSize(311, 620);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }

    public static void main(String[] args){
	GUI();
    }
}
