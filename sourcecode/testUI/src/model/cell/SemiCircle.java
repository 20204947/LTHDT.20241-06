package model.cell;

import model.gem.BigGem;

public class SemiCircle extends Cell{
	
	public SemiCircle() {
		super();
		this.getListGem().add(new BigGem(this.getId()));
	}
}
