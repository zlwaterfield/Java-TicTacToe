import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener
	{
		//Instance Variables
		private int[][] winCombinations = new int[][] {
				
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
			{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //virticle wins
			{0, 4, 8}, {2, 4, 6} //diagonal wins
		};
		
		private JFrame window = new JFrame("Tic-Tac-Toe");
		private JButton buttons[] = new JButton[9];
		private int count = 0;
		private String letter = "";
		private boolean win = false;
		private static int startCount =0;
		JMenuBar menu = new JMenuBar();
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem exit = new JMenuItem("Exit");
		static String x = "X";
		static String y = "Y";
		
		public TicTacToe() {
			//Create Window
			window.setSize(370,370);
			window.setLocationRelativeTo(null);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setLayout(new GridLayout(3,3));
			
			//Add Buttons To The Window
			for(int i=0; i<=8; i++) {
				
				buttons[i] = new JButton();
				window.add(buttons[i]);
				buttons[i].addActionListener(this);
			}
		
			menu.add(newGame);
			menu.add(exit);
			
			newGame.addActionListener(this);
			exit.addActionListener(this);
			window.setJMenuBar(menu);
			//Make The Window Visible
			window.setVisible(true);
		}
		
		public void setName() {
			
			x = JOptionPane.showInputDialog(null, "Enter Name of player X: ", "", 1);
			y = JOptionPane.showInputDialog(null, "Enter Name of player O: ", "", 1);
			if(x==null) {
				
				x = "X";
			}
			
			if(y==null) {
				
				y = "O";
			}
			
			if(x.length()==0) {
				
				x = "X";
			}
			
			if(y.length()==0) {
				
				y = "O";
			}
			
			JOptionPane.showMessageDialog(null, "Your names have been set\n Good luck " + x + " and " + y + "!",null,JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		public void actionPerformed(ActionEvent a) {
			
			Object source = a.getSource();
			
			if(source == newGame) {
					
					this.clearIt();
					
			} else if(source == exit) {
				
				int answer = JOptionPane.showConfirmDialog(null, "EXIT", "Are You sure you want to exit??", JOptionPane.YES_NO_OPTION);
			
				if (answer == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
					System.exit(0);
				}
			} else {
				
				count++;
			
			/*Calculate whose turn it is*/
			if(count % 2 == 0) {
				
				letter = "O";
			} else {
				
			letter = "X";
		}
		
		/*Write the letter to the button and deactivate it*/
		JButton pressedButton = (JButton)source;
		pressedButton.setText(letter);
		pressedButton.setEnabled(false);
		
		/*Determine who won*/
		for(int i=0; i<=7; i++) {
			
			if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) &&
			buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) &&
			buttons[winCombinations[i][0]].getText() != "") {
		
				win = true;
			}
		}
		
		/*Show a dialog when game is over*/
		if(win == true) {
			
			if(letter.equals("X")) {
				
				letter = x;
			} else {
				
				letter = y;
			}
			
			JOptionPane.showMessageDialog(null, letter + " wins the game!");
			int answer = JOptionPane.showConfirmDialog(null, "Start", "Do you want to start a new game", JOptionPane.YES_NO_OPTION);
			
			if (answer == JOptionPane.YES_OPTION) {
			
				this.clearIt();
			} else {
			
				JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
				System.exit(0);
			}
		} else if(count == 9 && win == false) {
		
			JOptionPane.showMessageDialog(null, "The game was tie!");
			int answer = JOptionPane.showConfirmDialog(null, "Start", "Do you want to start a new game", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				
				this.clearIt();
			} else {
				
				JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
				System.exit(0);
				}
			}
		}
	}
		
	public void clearIt() {
		
		window.setVisible(false);
		//this.window = null;
		this.startIt();
	}
		
	public void startIt() {
		
		new TicTacToe();
	}
		
	public static void main(String[] args) {
		
		TicTacToe starter = new TicTacToe();
		starter.setName();
	}
}