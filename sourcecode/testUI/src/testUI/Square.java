package testUI;

import java.util.ArrayList;
import java.util.List;

public class Square extends Cell{
	
	public Square() {
		super();
		for(int i = 0; i<5; i++) {
			this.getListGem().add(new SmallGem(this.getId()));
		}
	}
	
////////////////////////////////////////////////
//	private int id;
//	private List<Gem> listGem = new ArrayList<Gem>();
//	private int value;
//	static int count = 0;
//	
//	
//	public Square() {
//		this.id = count;
//		if((this.id%6)==0) {
//			listGem.add(new BigGem(this.id));
//		}else {
//			for(int i = 0; i<5; i++) {
//				listGem.add(new SmallGem(this.id));
//			}
//		}
//		count++;
//	}
//	
//	public void add(Gem gem) {
//		listGem.add(gem);
//		}
//	
//	public void remove(Gem gem) {
//		if(listGem.contains(gem)) {
//			listGem.remove(gem);
//		}
//	}
//	
//	
//	public int getValue() {
//		int total = 0;
//		for (Gem g : listGem) {
//			total += g.getValue();
//		}
//		return  total;
//	}
//
//	public void valueCaculate() {
//		int total = 0;
//		for (Gem g : listGem) {
//			total += g.getValue();
//		}
//		this.value = total;
//	}
//
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public List<Gem> getListGem() {
//		return listGem;
//	}
//	public void setListGem(List<Gem> listGem) {
//		this.listGem = listGem;
//	}
}
