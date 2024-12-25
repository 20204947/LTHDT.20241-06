package model.cell;

import java.util.ArrayList;
import java.util.List;
import model.gem.Gem;

public abstract class Cell {
	private int id;
	private final List<Gem> listGem = new ArrayList<Gem>();
	private int value;
	static int count = 0;
	
	
	public Cell() {
		this.id = count;
		count++;
	}
	
	public void add(Gem gem) {
		listGem.add(gem);
		}
	
	public void remove(Gem gem) {
        listGem.remove(gem);
	}
	
	
	public int getValue() {
		int total = 0;
		for (Gem g : listGem) {
			total += g.getValue();
		}
		return  total;
	}

	public int getId() {
		return id;
	}
	
	public List<Gem> getListGem() {
		return listGem;
	}
}
