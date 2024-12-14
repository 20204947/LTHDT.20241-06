package testUI;

public class GameBoard {
	
	private Player player1 = new Player(true);
	private Player player2 = new Player(false);
	
	private Square[] listCell = new Square[12];
	
	public GameBoard() {
		for(int i = 0; i<12; i++) {
			listCell[i] = new Square();
		}
	}

	public Square[] getListCell() {
		return listCell;
	}

	public void setListCell(Square[] listCell) {
		this.listCell = listCell;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
}
