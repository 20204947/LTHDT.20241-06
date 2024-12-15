package testUI;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell {
	private int id;
	private List<Gem> listGem = new ArrayList<Gem>();
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
		if(listGem.contains(gem)) {
			listGem.remove(gem);
		}
	}
	
	
	public int getValue() {
		int total = 0;
		for (Gem g : listGem) {
			total += g.getValue();
		}
		return  total;
	}

	public void valueCaculate() {
		int total = 0;
		for (Gem g : listGem) {
			total += g.getValue();
		}
		this.value = total;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Gem> getListGem() {
		return listGem;
	}
	public void setListGem(List<Gem> listGem) {
		this.listGem = listGem;
	}
}
