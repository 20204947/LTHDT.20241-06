package model.game_board;

import model.cell.Cell;
import model.cell.SemiCircle;
import model.cell.Square;
import model.player.Player;

public class GameBoard {
	
	private Player player1 = new Player(true);
	private Player player2 = new Player(false);
	
	private Cell[] listCell = new Cell[12];
	public GameBoard() {
		for(int i = 0; i<12; i++) {
			if((i%6)==0) {
				listCell[i] = new SemiCircle();
			}else {
				listCell[i] = new Square();
			}
		}
	}

	public Cell[] getListCell() {
		return listCell;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
}
