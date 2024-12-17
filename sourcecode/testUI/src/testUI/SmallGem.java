package testUI;

import java.io.File;

import javafx.scene.image.ImageView;

public class SmallGem extends Gem{
	
	public SmallGem(int inCell) {
		super(inCell);
		this.setValue(1);
		this.setImage(setImg());
	}
	
	public ImageView setImg() {
    	File file = new File("C:\\\\Users\\\\ADMIN\\\\Desktop\\\\testUI\\\\testUI\\\\src\\\\testUI\\\\small-gem.png");
    	ImageView img = new ImageView(file.toURI().toString());
        img.setFitWidth(20);
        img.setFitHeight(20);
        return img;
	}
}
