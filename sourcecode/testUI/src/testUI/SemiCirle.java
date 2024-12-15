package testUI;

public class SemiCirle extends Cell{
	
	public SemiCirle() {
		super();
		this.getListGem().add(new BigGem(this.getId()));
	}
}
