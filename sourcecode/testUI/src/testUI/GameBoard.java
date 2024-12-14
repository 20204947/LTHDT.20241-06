package testUI;

public class GameBoard {
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
}
