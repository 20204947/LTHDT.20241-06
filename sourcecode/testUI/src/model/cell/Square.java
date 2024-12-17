package model.cell;

import model.gem.SmallGem;

public class Square extends Cell{
	
	public Square() {
		super();
		for(int i = 0; i<5; i++) {
			this.getListGem().add(new SmallGem(this.getId()));
		}
	}
}
