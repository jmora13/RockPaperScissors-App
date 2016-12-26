//Jose Mora
//11-29-15
//Project 7 GUIs II

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RPSGUIGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties
	private JLabel statusC, statusU, statusT, balance;

	// images and labels to display the computer and user's moves and the outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;
	
	// the game object
	private RPS game;

	public RPSGUIGame(double numBet) {
		
		super("CHOOSE YOUR WEAPON!");
		this.game = new RPS(numBet);

		// initializes the window
		setSize(500, 400);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		setResizable(false);

		// creates the game object
		this.game = new RPS(numBet);
		rockImage = new ImageIcon("rock.jpg");
		paperImage = new ImageIcon("paper.jpg");
		scissorsImage = new ImageIcon("scissors.jpg");

		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.cyan);
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		userPlay.setForeground(Color.cyan);
		
		outcome = new JLabel();
		outcome.setHorizontalTextPosition(SwingConstants.CENTER);
		outcome.setForeground(Color.pink);
		
		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.black);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(compPlay, BorderLayout.WEST);
		imageOutcomePanel.add(userPlay, BorderLayout.EAST);
		imageOutcomePanel.add(outcome, BorderLayout.SOUTH);
		
		// creates the labels for the status of the game (number of wins, losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel("Computer Wins: " + game.getComputerWins());
		statusU = new JLabel("User Wins: " + game.getUserWins());
		statusT = new JLabel("Ties: " + game.getTies());
		balance = new JLabel("Cash: $" + this.game.getMoneyLeft());
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);
		balance.setForeground(Color.white);
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusC);
		statusPanel.add(statusU);
		statusPanel.add(statusT);
		statusPanel.add(balance);

		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.black);
		gamePanel.add(imageOutcomePanel);
		gamePanel.add(statusPanel);
		
		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.black);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	/* determines which button was clicked and updates the game accordingly */
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int userPlay = 0;
			Object source = event.getSource();
			if (source == RPSGUIGame.this.rockButton) {
				userPlay = 1;
			} else if(source == RPSGUIGame.this.paperButton) {
				userPlay = 2;
			} else if(source == RPSGUIGame.this.scissorsButton) {
				userPlay =  3;
			}
			RPSGUIGame.this.userDisplay(userPlay);
			int compPlay = RPSGUIGame.this.game.computerMove();
			RPSGUIGame.this.computerDisplay(compPlay);
			int result = RPSGUIGame.this.game.findWinner(userPlay, compPlay);
			RPSGUIGame.this.updateDisplay(result, userPlay, compPlay);
		}
	}
	
	private void computerDisplay(int CD) {
		if (CD == 1) {
			this.compPlay.setIcon(this.rockImage);
		} else if (CD == 2) {
			this.compPlay.setIcon(this.paperImage);
		} else if (CD == 3) {
			this.compPlay.setIcon(this.scissorsImage);
		}
		this.compPlay.setText("Computer's Move.");
	}
	
	private void userDisplay(int UD) {
		if ( UD == 1) {
			this.userPlay.setIcon(this.rockImage);
		} else if (UD == 2) {
			this.userPlay.setIcon(paperImage);
		} else if (UD == 3) {
			this.userPlay.setIcon(scissorsImage);
		}
		this.userPlay.setText("Your Move.");
	}
	
	private void updateDisplay(int result, int userMove, int computerMove) {
		if (result == 3) {
			this.outcome.setText("Tie!");
		} else if (result == 1) {
			if(computerMove == 1) {
				this.outcome.setText("The Rock beats Scissors!");
			} else if (computerMove == 2) {
				this.outcome.setText("Paper covers The Rock!");
			} else if (computerMove == 3) {
				this.outcome.setText("Scissors cuts paper!");
			}
		} else if (result == 2) {
		 if(userMove == 1) {
			 this.outcome.setText("The Rock beats Scissors!");
		 } else if (userMove == 2) {
			 this.outcome.setText("Paper covers The Rock!");
		 } else if (userMove == 3) {
			 this.outcome.setText("Scissors cuts paper!");
		 }
		}
		 this.statusT.setText("Ties: " + this.game.getTies());
		 this.statusU.setText("User Wins: " + this.game.getUserWins());
		 this.statusC.setText("Computer Wins: " + this.game.getComputerWins());
		 this.balance.setText("Cash: $" + this.game.getMoneyLeft());
	}
	
	public static void main(String args[]) {
		// create an object of your class
		double numBet = 0;
		
		JOptionPane.showMessageDialog(null, "ARE YOU READY TO THROW DOWN?!?", "", JOptionPane.OK_OPTION);
		if(JOptionPane.showConfirmDialog(null, "Would you like to wager a bet?","", JOptionPane.YES_NO_OPTION) == 0) {
			numBet = Double.parseDouble(JOptionPane.showInputDialog("How much would you like to bet?"));
		}
		RPSGUIGame frame = new RPSGUIGame(numBet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}