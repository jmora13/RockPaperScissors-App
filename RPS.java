import java.util.Random;

public class RPS {
	private int computerWins, userWins, ties;
	private double moneyLeft, numBet;
	private boolean isBetting;
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;	
	public static final int CPU_WIN = 1;
	public static final int USER_WIN = 2;
	public static final int TIE = 3;
	
	public RPS (double numBet) {
		this.computerWins = 0;
		this.userWins = 0;
		this.ties = 0;
		this.moneyLeft = 0;
		this.numBet = numBet;
		
		if(numBet == 0) {
			this.isBetting = false;
		} else {
			this.isBetting = true;
		}
	}
	
	public int computerMove() {
		Random r = new Random();
		int computerMove = r.nextInt(3) + 1;
		return computerMove;
	}
	
	public int getComputerWins() {
		return this.computerWins;
	}
	
	public int getUserWins() {
		return this.userWins;
	}
	
	public int getTies(){
		return this.ties;
	}
	
	public boolean isBetting() {
		return this.isBetting;
	}
	
	public double getMoneyLeft() {
		return this.moneyLeft;
	}
	
	public int findWinner(int userMove, int computerMove) {
		if (userMove == computerMove) {
			this.ties++;
			return 3;
		}
		if (userMove == 1) {
			if (computerMove == 2) {
				computerWins++;
				moneyLeft -= numBet;
				return 1;
			}
			this.userWins++;
			moneyLeft += numBet;
			return 2;
		}
		if (userMove == 2) {
			if(computerMove == 3) {
				computerWins++;
				moneyLeft -= numBet;
				return 1;
			}
			this.userWins++;
			moneyLeft += numBet;
			return 2;
		}
		  if (computerMove == 1) {
			  computerWins++;
			  moneyLeft -= numBet;
			  return 1;
		  }
		  this.userWins++;
		  moneyLeft += numBet;
		  return 2;
		}
	}

