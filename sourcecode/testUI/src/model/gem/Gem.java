package model.gem;

import javafx.scene.image.ImageView;

public abstract class Gem {
	private int id;
	private int value;
	private ImageView image;
	static int count = 0;
	
	public Gem(int inCell) {
		count++;
		this.id = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}	
}
