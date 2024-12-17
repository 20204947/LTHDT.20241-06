package model.gem;

import java.io.File;

import javafx.scene.image.ImageView;

public class BigGem extends Gem{

	public BigGem(int inCell) {
		super(inCell);
		this.setValue(10);
		this.setImage(setImg());
	}
	
	public ImageView setImg() {
    	File file = new File("C:\\Users\\ADMIN\\Desktop\\xxx\\testUI\\src\\view\\image\\big-gem.png");
    	ImageView img = new ImageView(file.toURI().toString());
        img.setFitWidth(50);
        img.setFitHeight(50);
        return img;
	}
	
}
