package model.game_board;

import model.cell.Cell;
import model.cell.SemiCircle;
import model.cell.Square;
import model.player.Player;

public class GameBoard2 {
	
	private Player player = new Player(true);
	
	private Cell[] listCell = new Cell[12];
	public GameBoard2() {
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
	
	public Player getPlayer() {
		return player;
	}
}
